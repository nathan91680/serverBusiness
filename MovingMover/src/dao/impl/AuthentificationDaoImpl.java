package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import dao.DAOFactory;
import dao.DAOUtils;
import dao.api.IAuthentificationDao;
import dao.exception.DAOException;
import session.impl.Authentification;
import utils.QueryHelper;

@Repository("authentificationDao")
public class AuthentificationDaoImpl implements IAuthentificationDao{
	
	private static final String SQL_AUTHENTIFICATION = QueryHelper.loadResourceToString("authentification");

	@Override
	public Boolean getAuthentification(Authentification authentification) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Boolean success = Boolean.FALSE;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_AUTHENTIFICATION, false, authentification.geteMail(),authentification.getPwd() );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				success = Boolean.TRUE;
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return success;
	}

}
