package es.ubiqua.compareme.tasks;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler {
	public QuartzScheduler(){
		
		JobDetail job = JobBuilder.newJob(CurrencyTableTask.class).withIdentity("currencyTable", "group2").build();
		
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("currencyTable", "group2")
				.withSchedule(
					SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(5).repeatForever())
				.build();
		try{
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
