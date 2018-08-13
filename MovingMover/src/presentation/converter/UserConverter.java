package presentation.converter;

import bean.User;
import presentation.dto.UserDto;

public class UserConverter {
	
	public static UserDto toDto(User user) throws Exception {
		UserDto userDto = new UserDto();
		userDto.seteMail(user.geteMail());
		userDto.setFirstname(user.getFirstname());
		userDto.setLastname(user.getLastname());
		userDto.setPwd(user.getPwd());
		return userDto;
	}

}
