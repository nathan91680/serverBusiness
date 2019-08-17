package servlets.api;

import org.springframework.http.ResponseEntity;

import presentation.dto.AuthentificationDto;

public interface IAuthentificationController {
	
	public ResponseEntity<Boolean> getAuthentification(AuthentificationDto authentificationDt);

}
