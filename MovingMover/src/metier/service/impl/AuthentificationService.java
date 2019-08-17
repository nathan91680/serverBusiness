package metier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.api.IAuthentificationDao;
import metier.service.api.IAuthentificationService;
import session.impl.Authentification;

@Service("authentificationService")
public class AuthentificationService implements IAuthentificationService{
	
	@Autowired
	IAuthentificationDao authentificationDao;

	@Override
	public Boolean authentification(Authentification authentification) throws Exception {
		Boolean success = authentificationDao.getAuthentification(authentification);
		return success;
	}

	

}
