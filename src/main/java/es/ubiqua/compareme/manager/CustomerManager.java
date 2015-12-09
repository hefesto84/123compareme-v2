package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.CustomerDAO;
import es.ubiqua.compareme.exceptions.CustomerException;
import es.ubiqua.compareme.model.Customer;

public class CustomerManager {
	
	private CustomerDAO customerDao = new CustomerDAO();
	
	public CustomerManager(){
		
	}
	
	public List<Customer> list() throws CustomerException{
		return customerDao.list();
	}
	
	public List<Customer> list(Customer c) throws CustomerException{
		return customerDao.list(c);
	}
	
	public List<Customer> listAllCustomers(Customer c){
		return customerDao.listAllCustomers(c);
	}

	public Customer get(Customer customer) throws CustomerException{
		return customerDao.get(customer);
	}
	
	public Customer login(Customer customer) throws CustomerException{
		return customerDao.login(customer);
	}
	
	public Customer add(Customer customer) throws CustomerException{
		return customerDao.add(customer);
	}
	
	public void del(Customer customer) throws CustomerException{
		customerDao.del(customer);
	}
}
