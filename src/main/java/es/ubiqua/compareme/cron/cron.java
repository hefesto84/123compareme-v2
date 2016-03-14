package es.ubiqua.compareme.cron;

import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.*;

public class cron extends HttpServlet{
	
	public void init() throws ServletException
    {
		cronSerhsJob();
    }
	
	public void cronSerhsJob(){
		try {
			String triggerName = "Serhs Trigger";
			JobDetail cronSerhs = JobBuilder.newJob(cronSerhs.class).withIdentity("serhs", "serhs").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, "serhs").withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 * * ? *")).build();
			//Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, "serhs").withSchedule(CronScheduleBuilder.cronSchedule("0 5 8 * * ? *")).build();
			Scheduler scheduler;
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(cronSerhs, trigger);
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
