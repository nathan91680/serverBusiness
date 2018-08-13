package presentation.dto;

public class HistoryEventDto {
	public EventDto eventDto;
	int timeExecution;
	/**
	 * @return the eventDto
	 */
	public EventDto getEventDto() {
		return eventDto;
	}
	/**
	 * @param eventDto the eventDto to set
	 */
	public void setEventDto(EventDto eventDto) {
		this.eventDto = eventDto;
	}
	/**
	 * @return the timeExecution
	 */
	public int getTimeExecution() {
		return timeExecution;
	}
	/**
	 * @param timeExecution the timeExecution to set
	 */
	public void setTimeExecution(int timeExecution) {
		this.timeExecution = timeExecution;
	}
	
	
}
