package dao.api;

import bean.IAEntity;
import dao.exception.DAOException;

public interface IIAEntityDao {
	
	IAEntity create( IAEntity iAEntity ) throws DAOException;

	IAEntity find( Integer id ) throws DAOException;
    
	IAEntity update( IAEntity iAEntity ) throws DAOException;
    
    Boolean delete( Integer id ) throws DAOException;

}
