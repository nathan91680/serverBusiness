package metier.service.api;

import bean.User;

public interface IUserService {
	
	public Integer createUser(String firstname, String lastname, String eMail, String pwd);
	
	public User findUser(Integer id);

}
