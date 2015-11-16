package es.ubiqua.compareme.tasks;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CurrencyTableTask implements Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Hello Quartz");
	}

}
