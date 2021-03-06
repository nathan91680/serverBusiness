package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import bean.User;
import dao.DAOFactory;
import dao.DAOUtils;
import dao.api.IUserDao;
import dao.exception.DAOException;
import utils.QueryHelper;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{


	private static final String SQL_FIND_USER_BY_ID = QueryHelper.loadResourceToString("getUser");
	
	private static final String SQL_CREATE_USER = QueryHelper.loadResourceToString("createUser");
	
	private static final String SQL_UPDATE_USER = QueryHelper.loadResourceToString("updateUser");
	
	private static final String SQL_DELETE_USER = QueryHelper.loadResourceToString("deleteUser");


	@Override
	public User create(User user) throws DAOException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_CREATE_USER, true, user.getLastname(), user.getFirstname(), user.geteMail(), user.getPwd());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
				user.setId( valeursAutoGenerees.getInt( 1 ) );
			} else {
				throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
		}

		return user;
	}
	
	@Override
	public User update(User user) throws DAOException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_UPDATE_USER, false, user.getLastname(), user.getFirstname(), user.geteMail(), user.getPwd(), user.getId());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la modification de l'utilisateur, aucune modification de la table." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return user;
	}
	
	@Override
	public Boolean delete(Integer id) throws DAOException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Boolean success = Boolean.TRUE;
		try {
			
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_DELETE_USER, false, id);
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête de supression */
			if ( statut == 0 ) {
				success = Boolean.FALSE;
				throw new DAOException( "Échec de la supression de l'utilisateur, aucune modification de la table." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return success;
	}

	@Override
	public User find(Integer id) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_FIND_USER_BY_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				user = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return user;
	}

	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static User map( ResultSet resultSet ) throws SQLException {
		User user = new User();
		user.setId( resultSet.getInt( "id" ) );
		user.seteMail( resultSet.getString( "email" ) );
		user.setPwd( resultSet.getString( "pwd" ) );
		user.setFirstname( resultSet.getString( "firstname" ) );
		user.setLastname( resultSet.getString( "lastname" ) );
		return user;
	}

}
