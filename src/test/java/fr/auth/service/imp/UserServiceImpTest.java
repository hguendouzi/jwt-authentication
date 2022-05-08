package fr.auth.service.imp;

import fr.auth.dto.UserDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.UserMapper;
import fr.auth.repository.UserRepository;
import fr.auth.utils.MockData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * 
 * @author hicham
 *
 */
@ExtendWith(SpringExtension.class)
class UserServiceImpTest {
	
	@InjectMocks
	private UserServiceImp service;
	
	@Mock
	private UserRepository repository;
	@Mock
	private UserMapper mapper;

	
	

    @Test
	@DisplayName("Test service save or update user")
	void should_be_save_or_update_user() throws GlobalException {
		when(mapper.toUser(Mockito.any(UserDto.class))).thenReturn(MockData.mockUser());
		when(repository.save(MockData.mockUser())).thenReturn(MockData.mockUser());
		when(mapper.toUserDto(MockData.mockUser())).thenReturn(MockData.mockUserDto());
		UserDto response = service.saveOrUpdateUser(MockData.mockUserDto());
		assertThat(response).isNotNull();
	}
	
	
	@Test
	@DisplayName("Test find  user by email")
	void should_be_find_user_by_email() throws GlobalException {
		when(mapper.toUserDto(MockData.mockUser())).thenReturn(MockData.mockUserDto());
		when(repository.findByEmail("test@test.fr")).thenReturn(MockData.mockUser());
		UserDto response = service.findByEmail("test@test.fr");
		assertThat(response).isNotNull();
	}
	
	@Test
	@DisplayName("Test find all users")
	void should_find_all_user() throws GlobalException {
		when(mapper.toListUserDto(List.of(MockData.mockUser()))).thenReturn(List.of(MockData.mockUserDto()));
		when(repository.findAll()).thenReturn(List.of(MockData.mockUser()));
	     List<UserDto> list = service.findAllUsers();
		assertThat(list).isNotEmpty();
	}

}
