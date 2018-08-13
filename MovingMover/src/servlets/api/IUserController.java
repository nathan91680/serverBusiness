package servlets.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import bean.User;
import presentation.dto.UserDto;

public interface IUserController {
	
	public ResponseEntity<List<User>> getUsers();
	public ResponseEntity<UserDto> getUser(Integer id);
	public ResponseEntity<User> createUser(User user);
	public ResponseEntity<User> modifyUser(User user);
	public ResponseEntity<Boolean> deleteUser(Integer id);

}
