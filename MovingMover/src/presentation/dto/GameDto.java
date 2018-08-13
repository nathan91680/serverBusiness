 package presentation.dto;

import java.util.List;

public class GameDto {
	
	private MapDto mapDto;
	private List<HistoryEventDto> histoEventsDto;
	
	

	/**
	 * @return the histoEventsDto
	 */
	public List<HistoryEventDto> getHistoEventsDto() {
		return histoEventsDto;
	}

	/**
	 * @param histoEventsDto the histoEventsDto to set
	 */
	public void setHistoEventsDto(List<HistoryEventDto> histoEventsDto) {
		this.histoEventsDto = histoEventsDto;
	}

	/**
	 * @return the mapDto
	 */
	public MapDto getMapDto() {
		return mapDto;
	}

	/**
	 * @param mapDto the mapDto to set
	 */
	public void setMapDto(MapDto mapDto) {
		this.mapDto = mapDto;
	}
	
	

}
