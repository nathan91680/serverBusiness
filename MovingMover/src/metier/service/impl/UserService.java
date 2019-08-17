package metier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.User;
import dao.api.IUserDao;
import metier.service.api.IUserService;


@Service("userService")
public class UserService implements IUserService{
	
	@Autowired
	IUserDao userDao;
	
	@Override
	public User createUser(User user) {

		User createdUser = userDao.create(user);
		return createdUser;
	}

	@Override
	public User findUser(Integer id) {
		
		User user = userDao.find(id);
		return user;
	}

	@Override
	public User updateUser(User user) {
		User updatedUser = userDao.update(user);
		return updatedUser;
	}

	@Override
	public Boolean deleteUser(Integer id) {
		
		Boolean success = userDao.delete(id);
		return success;
	}

}
