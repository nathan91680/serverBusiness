package dao.api;

import dao.exception.DAOException;
import session.impl.Authentification;

public interface IAuthentificationDao {
	Boolean getAuthentification( Authentification authentification ) throws DAOException;
}
