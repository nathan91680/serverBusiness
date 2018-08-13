package servlets.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bean.User;
import metier.service.api.IUserService;
import presentation.converter.UserConverter;
import presentation.dto.UserDto;
import servlets.abstr.CustomHttpServlet;
import servlets.api.IUserController;

@RestController
public class UserController extends CustomHttpServlet implements IUserController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUserService userService;
	
	@Override
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> getUsers() {
		// TODO Auto-generated method stub
		/* Transmission de la paire d'objets request/response à notre JSP */
		List<User> users = new ArrayList<User>();
		ResponseEntity<List<User>> response = new ResponseEntity<List<User>>(users,HttpStatus.FOUND);
		return response;
	}
	
	@Override
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
		User user = userService.findUser(id);
		UserDto userDto = new UserDto();
		try {
			userDto = UserConverter.toDto(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<UserDto> response = new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
		return response;
	}
	
	@Override
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> createUser(@RequestBody User user) {
		// TODO Auto-generated method stub
		/* Transmission de la paire d'objets request/response à notre JSP */
		ResponseEntity<User> response = new ResponseEntity<User>(user,HttpStatus.FOUND);
		return response;
	}
	
	@Override
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<User> modifyUser(@RequestBody User user) {
		// TODO Auto-generated method stub
		/* Transmission de la paire d'objets request/response à notre JSP */
		ResponseEntity<User> response = new ResponseEntity<User>(user,HttpStatus.FOUND);
		return response;
	}
	
	@Override
	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteUser(@RequestBody Integer id) {
		// TODO Auto-generated method stub
		/* Transmission de la paire d'objets request/response à notre JSP */
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.FOUND);
		return response;
	}

}
