package fr.auth.service.imp;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.auth.dto.UserDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.UserMapper;
import fr.auth.model.User;
import fr.auth.repository.UserRepository;
import fr.auth.service.UserService;

/**
 * 
 * @author hicham
 *
 */
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository repository;

	private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

	@Override
	public UserDto saveOrUpdateUser(UserDto dto) throws GlobalException {
		if (dto.getId() == null && findByEmail(dto.getEmail()) != null)
			      throw new GlobalException("login", "login already exists");
		User user = repository.save(mapper.toUser(dto));
		return mapper.toUserDto(user);
	}

	@Override
	public UserDto findByEmail(String email) throws GlobalException {
		User user = repository.findByEmail(email);
		return mapper.toUserDto(user);
	}

	@Override
	public List<UserDto> deleteByEmail(String email) throws GlobalException {
		repository.deleteByEmail(email);
		return mapper.toListUserDto((List<User>) repository.findAll());

	}

	@Override
	public List<UserDto> findAllUsers() throws GlobalException {
		List<User> users = (List<User>) repository.findAll();
		return mapper.toListUserDto(users);
	}

}
