package metier.service.api;

import bean.User;

public interface IUserService {
	
	public User createUser(User user) throws Exception;
	
	public User findUser(Integer id) throws Exception;
	
	public User updateUser(User user) throws Exception;
	
	public Boolean deleteUser(Integer id) throws Exception;

}
