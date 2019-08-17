package dao.api;

import bean.User;
import dao.exception.DAOException;

public interface IUserDao {
	
	User create( User user ) throws DAOException;

    User find( Integer id ) throws DAOException;
    
    User update( User user ) throws DAOException;
    
    Boolean delete( Integer id ) throws DAOException;

}
