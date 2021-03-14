package fr.auth.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.auth.dto.UserDto;
import fr.auth.security.JwtSecurityConfigTest;
import fr.auth.service.UserService;
import fr.auth.utils.MockData;

/**
 * 
 * @author hicham test call methods of service
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = JwtSecurityConfigTest.class)
class UserControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@MockBean
	private UserService service;

	@Autowired
	private ObjectMapper objectMapper;

	private final MockData mockData = new MockData();

	protected JacksonTester<UserDto> jsonDto;

	@BeforeEach
	void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(springSecurity()).build();

		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	@DisplayName("Test operation save or update user  without logging in")
	void should_be_return_401_when_call_operation_saveOrUpade_without_loggiin_in()
			throws Exception {
		Mockito.when(service.saveOrUpdateUser(mockData.mockUserDto()))
				.thenReturn(mockData.mockUserDto());
		MockHttpServletResponse response = (MockHttpServletResponse) mvc
				.perform(MockMvcRequestBuilders.post("/user/create")
						.content(
								jsonDto.write(mockData.mockUserDto()).getJson())
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertThat(response.getStatus())
				.isEqualTo(HttpStatus.UNAUTHORIZED.value());

	}

	@Test
	@DisplayName("Test operation save or update user with not authorites admin")
	@WithUserDetails("manager")
	void should_be_return_401_when_call_operation_saveOrUpade_with_not_authorites_admin()
			throws Exception {
		Mockito.when(service.saveOrUpdateUser(mockData.mockUserDto()))
				.thenReturn(mockData.mockUserDto());
		MockHttpServletResponse response = (MockHttpServletResponse) mvc
				.perform(MockMvcRequestBuilders.post("/user/create")
						.content(
								jsonDto.write(mockData.mockUserDto()).getJson())
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertThat(response.getStatus())
				.isEqualTo(HttpStatus.FORBIDDEN.value());

	}

	@Test
	@DisplayName("Test operation save or update user with authorites Admin")
	@WithUserDetails("admin")
	void should_be_return_200_with_user_when_call_operation_saveOrUpade_with_authorites_admin()
			throws Exception {
		Mockito.when(service.saveOrUpdateUser(mockData.mockUserDto()))
				.thenReturn(mockData.mockUserDto());
		MockHttpServletResponse response = (MockHttpServletResponse) mvc
				.perform(MockMvcRequestBuilders.post("/user/create")
						.content(
								jsonDto.write(mockData.mockUserDto()).getJson())
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}

	@Test
	@DisplayName("Test operation find all user")
	@WithMockUser()
	void should_be_return_200_with_list_of_user_when_call_operation_findAllUsers()
			throws Exception {

		Mockito.when(service.findAllUsers())
				.thenReturn(List.of(mockData.mockUserDto()));
		MockHttpServletResponse response = (MockHttpServletResponse) mvc
				.perform(MockMvcRequestBuilders.get("/user/all")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[0].email", Matchers.is("test@test.fr")))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}

	@Test
	@DisplayName("Test operation find user by email")
	@WithMockUser()
	void should_be_return_200_user_found_when_call_operation_findByEmail()
			throws Exception {
		Mockito.when(service.findByEmail("test@test.fr"))
				.thenReturn(mockData.mockUserDto());
		MockHttpServletResponse response = (MockHttpServletResponse) mvc
				.perform(MockMvcRequestBuilders.get("/user/test@test.fr")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.email", Matchers.is("test@test.fr")))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}

	@Test
	@DisplayName("Test operation delete user by email")
	@WithUserDetails("admin")
	void should_be_return_200_with_rest_of_user_when_call_operation_deleteByEmail()
			throws Exception {

		Mockito.when(service.deleteByEmail("test@test.fr"))
				.thenReturn(List.of(mockData.mockUserDto()));
		MockHttpServletResponse response = (MockHttpServletResponse) mvc
				.perform(MockMvcRequestBuilders
						.delete("/user/delete/test@test.fr")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[0].email", Matchers.is("test@test.fr")))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}

}
