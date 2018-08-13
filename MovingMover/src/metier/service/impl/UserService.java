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
	public Integer createUser(String firstname, String lastname, String eMail, String pwd) {

		return 0;
	}

	@Override
	public User findUser(Integer id) {
		User user = userDao.find(id);
		return user;
	}

}
