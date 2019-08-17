package presentation.converter;

import bean.User;
import presentation.dto.UserDto;

public class UserConverter {
	
	public static UserDto toDto(User user) throws Exception {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.seteMail(user.geteMail());
		userDto.setFirstname(user.getFirstname());
		userDto.setLastname(user.getLastname());
		userDto.setPwd(user.getPwd());
		return userDto;
	}
	
	public static User toEntity(UserDto userDto) throws Exception {
		User user = new User();
		user.setId(userDto.getId());
		user.seteMail(userDto.geteMail());
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setPwd(userDto.getPwd());
		return user;
	}

}
