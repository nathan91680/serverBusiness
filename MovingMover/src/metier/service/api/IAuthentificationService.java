package metier.service.api;

import session.impl.Authentification;

public interface IAuthentificationService {
	
	public Boolean authentification(Authentification authentification) throws Exception;

}
