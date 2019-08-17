package servlets.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import metier.service.api.IAuthentificationService;
import presentation.converter.AuthentificationConverter;
import presentation.dto.AuthentificationDto;
import servlets.abstr.CustomHttpServlet;
import servlets.api.IAuthentificationController;
import session.impl.Authentification;

@RestController
public class AuthentificationController extends CustomHttpServlet implements IAuthentificationController{
	
private static final long serialVersionUID = 1L;
	
	@Autowired
	private IAuthentificationService authentificationService;

	@Override
	@RequestMapping(value = "/authentification", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Boolean> getAuthentification(@RequestBody AuthentificationDto authentificationDto) {
		Boolean success = null;
		ResponseEntity<Boolean> response = null;
		Authentification authentification = new Authentification();
		try {
			authentification = AuthentificationConverter.toEntity(authentificationDto);
			success = authentificationService.authentification(authentification);
			response = new ResponseEntity<Boolean>(success,HttpStatus.OK);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ResponseEntity<Boolean>(success,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
