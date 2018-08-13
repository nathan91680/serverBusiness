package servlets.api;

import org.springframework.http.ResponseEntity;

import presentation.dto.GameDto;

public interface IGameController {
	public ResponseEntity<GameDto> getGame();
}
