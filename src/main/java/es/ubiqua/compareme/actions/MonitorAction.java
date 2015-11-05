package es.ubiqua.compareme.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Ota;

public class MonitorAction  extends ActionSupport {

	private static final long serialVersionUID = 848628729489734130L;
	private List<Ota> monitor;
	
	public String execute() {
		
		setMonitor(new OtaManager().list());
		return SUCCESS;
	}

	public List<Ota> getMonitor() {
		return monitor;
	}

	public void setMonitor(List<Ota> monitor) {
		this.monitor = monitor;
	}

}

/*

Park Inn By Radisson Cologne City-West
Park Inn By Radisson Linz
Park Inn By Radisson Ostrava
Park Inn By Radisson Sarvar Resort
Park Inn By Radisson Mannheim
Park Inn Prague
Park Inn By Radisson Zurich Airport
Park Inn By Radisson Weimar
Radisson Blu Edwardian Bloomsbury Street Hotel
Radisson Blu Edwardian Heathrow Hotel
Radisson Blu Edwardian Hampshire Hotel
Radisson Blu Edwardian Mercer Street Hotel
Radisson Blu Edwardian Kenilworth Hotel
Radisson Blu Edwardian Vanderbilt Hotel
Radisson Blu Edwardian Leicester Square Hotel
Radisson Blu Edwardian Grafton Hotel
Radisson Blu Edwardian Sussex Hotel
Radisson Blu Edwardian Berkshire Hotel
Radisson Blu Edwardian Manchester Hotel
Radisson Blu Edwardian New Providence Wharf Hotel
Radisson Blu Edwardian Guildford Hotel

*/