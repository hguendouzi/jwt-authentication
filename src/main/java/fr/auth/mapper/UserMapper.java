package fr.auth.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.auth.dto.UserDto;
import fr.auth.model.User;

/**
 * 
 * @author hicham
 *
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
        
      
    UserDto toUserDto(User user);
    
    @Mapping(target = "password", source = "password", qualifiedByName = "encoderPassword")
	User toUser(UserDto user);

	List<UserDto> toListUserDto(List<User> list);

	List<User> toListUser(List<UserDto> list);
	
	
	@Named("encoderPassword")
	default String encoderPassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	


	

}
