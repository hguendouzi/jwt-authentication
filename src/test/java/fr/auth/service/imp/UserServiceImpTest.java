package fr.auth.service.imp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.auth.dto.UserDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.UserMapper;
import fr.auth.repository.UserRepository;
import fr.auth.utils.MockData;

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
	
	private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
	
	private final MockData mockData = new MockData();
	
	

	@BeforeEach
	void setUp() throws Exception {
	

		
	
	}

	@Test
	@DisplayName("Test service save or update user")
	void should_be_save_or_update_user() throws GlobalException {
		when(repository.save(mapper.toUser(mockData.mockUserDto()))).thenReturn(mockData.mockUser());
		UserDto response = service.saveOrUpdateUser(mockData.mockUserDto());
		assertThat(response).isNotNull();
	}
	
	
	@Test
	@DisplayName("Test find  user by email")
	void should_be_find_user_by_email() throws GlobalException {
		when(repository.findByEmail("test@test.fr")).thenReturn(mockData.mockUser());
		UserDto response = service.findByEmail("test@test.fr");
		assertThat(response).isNotNull();
	}
	
	@Test
	@DisplayName("Test find all users")
	void should_find_all_user() throws GlobalException {
		when(repository.findAll()).thenReturn(List.of(mockData.mockUser()));
	     List<UserDto> list = service.findAllUsers();
		assertThat(list).isNotEmpty();
	}

}
