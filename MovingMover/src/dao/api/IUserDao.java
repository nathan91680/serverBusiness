package dao.api;

import bean.User;
import dao.exception.DAOException;

public interface IUserDao {
	
	void create( User user ) throws DAOException;

    User find( Integer id ) throws DAOException;

}
