package fr.auth.service.imp;

import fr.auth.dto.UserDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.UserMapper;
import fr.auth.model.User;
import fr.auth.repository.UserRepository;
import fr.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hicham
 */
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {


    private final UserRepository repository;
    private final UserMapper mapper;

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
