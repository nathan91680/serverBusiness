package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import bean.IAEntity;
import dao.DAOFactory;
import dao.DAOUtils;
import dao.api.IIAEntityDao;
import dao.exception.DAOException;
import utils.QueryHelper;

@Repository("iAEntityDao")
public class IAEntityDaoImpl implements IIAEntityDao{
	
    private static final String SQL_FIND_IA_BY_ID = QueryHelper.loadResourceToString("getIA");
	
	private static final String SQL_CREATE_IA = QueryHelper.loadResourceToString("createIA");
	
	private static final String SQL_UPDATE_IA = QueryHelper.loadResourceToString("updateIA");
	
	private static final String SQL_DELETE_IA = QueryHelper.loadResourceToString("deleteIA");

	@Override
	public IAEntity create(IAEntity iAEntity) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_CREATE_IA, true, iAEntity.getName(), iAEntity.getAlgo());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création de l'IA, aucune ligne ajoutée dans la table." );
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
				iAEntity.setId( valeursAutoGenerees.getInt( 1 ) );
			} else {
				throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
		}

		return iAEntity;
	}

	@Override
	public IAEntity find(Integer id) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		IAEntity iAEntity = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_FIND_IA_BY_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				iAEntity = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return iAEntity;
	}

	@Override
	public IAEntity update(IAEntity iAEntity) throws DAOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_UPDATE_IA, false, iAEntity.getName(), iAEntity.getAlgo());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la modification de l'IA, aucune modification de la table." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return iAEntity;
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
			preparedStatement = DAOUtils.initialisationRequetePreparee( connexion, SQL_DELETE_IA, false, id);
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête de supression */
			if ( statut == 0 ) {
				success = Boolean.FALSE;
				throw new DAOException( "Échec de la supression de l'IA, aucune modification de la table." );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			DAOUtils.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return success;
	}
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static IAEntity map( ResultSet resultSet ) throws SQLException {
		IAEntity iAEntity = new IAEntity();
		iAEntity.setId( resultSet.getInt( "id" ) );
		iAEntity.setName( resultSet.getString( "name" ) );
		iAEntity.setAlgo( resultSet.getString( "algo" ) );
		
		return iAEntity;
	}

}
