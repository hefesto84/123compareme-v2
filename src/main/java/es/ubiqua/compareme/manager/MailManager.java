package es.ubiqua.compareme.manager;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import es.ubiqua.compareme.dao.CustomerDAO;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.utils.DBLogger;

public class MailManager {
	
	private CustomerDAO customerDao = new CustomerDAO();
	private Customer customer;
	
	public MailManager(String userId){
		Customer c = new Customer();
		c.setId(-1);
		c.setIdentifier(userId);
		customer = customerDao.get(c);
		DBLogger.getLogger().Error("Customer ID not found");
	}
	
	public void sendMail(String message){
		try{
			 Properties props = new Properties();
	         props.setProperty("mail.smtp.host", customer.getMhost());
	         props.setProperty("mail.smtp.port", "25");
	         props.setProperty("mail.smtp.user", customer.getMusername());
	         Session session = Session.getDefaultInstance(props);
	         MimeMessage msg = new MimeMessage(session);
	         msg.setFrom(new InternetAddress("contact@123compare.me"));
	         msg.addRecipient(Message.RecipientType.TO,new InternetAddress(customer.getContact()));
	         msg.setSubject("123Compare.Me Mail Information");
	         msg.setText(message);
	         Transport t = session.getTransport("smtp");
	         t.connect(customer.getMusername(),customer.getMpassword());
	         t.sendMessage(msg, msg.getAllRecipients());
	         t.close(); 
		}catch(Exception e){
			DBLogger.getLogger().Error(e.toString());
		}
	}
}
