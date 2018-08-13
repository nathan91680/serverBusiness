package session.impl;

import session.abstr.Event;

public class HistoryEvent {
	
	Integer timeExecution;
	Event event;
	/**
	 * @return the timeExecution
	 */
	public Integer getTimeExecution() {
		return timeExecution;
	}
	/**
	 * @param timeExecution the timeExecution to set
	 */
	public void setTimeExecution(Integer timeExecution) {
		this.timeExecution = timeExecution;
	}
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	
	

}
