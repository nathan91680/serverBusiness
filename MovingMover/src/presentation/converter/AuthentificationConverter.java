package presentation.converter;

import presentation.dto.AuthentificationDto;
import session.impl.Authentification;

public class AuthentificationConverter {
	
	public static AuthentificationDto toDto(Authentification authentification) throws Exception {
		AuthentificationDto authentificationDto = new AuthentificationDto();
		authentificationDto.seteMail(authentification.geteMail());
		authentificationDto.setPwd(authentification.getPwd());
		return authentificationDto;
	}
	
	public static Authentification toEntity(AuthentificationDto authentificationDto) throws Exception {
		Authentification authentification = new Authentification();
		authentification.seteMail(authentificationDto.geteMail());
		authentification.setPwd(authentificationDto.getPwd());
		return authentification;
	}

}
