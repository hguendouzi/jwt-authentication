package fr.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.auth.JwtAuthenticationApplication;
import fr.auth.dto.LoginDto;
import fr.auth.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author hguendouzi
 */
@SpringBootTest(classes = JwtAuthenticationApplication.class)
class LoginControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    @MockBean
    LoginService  service;

    protected JacksonTester<LoginDto> jsonLogin;

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @DisplayName("Test Operation login")
    @Test
    void should_return_token_in_header_when_login_is_successful() throws Exception {
        LoginDto dto = new LoginDto();
        dto.setEmail("hicham@hicham.com");
        dto.setPassword("admin1234");

        Mockito.when(service.login(dto)).thenReturn("zaghzcahgfzahvhgazhfaravhfh");
        MockHttpServletResponse response = mvc.perform(
                        MockMvcRequestBuilders.post("/login")
                                .content(jsonLogin.write(dto).getJson())
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getHeader("ACCESS-TOKEN")).isEqualTo("zaghzcahgfzahvhgazhfaravhfh");
    }

    @DisplayName("Test Operation login")
    @Test
    void should_throw_when_password_least_6_characters() throws Exception {
        LoginDto dto = new LoginDto();
        dto.setEmail("hicham@hicham.com");
        dto.setPassword("admin");

        Mockito.when(service.login(dto)).thenReturn("zaghzcahgfzahvhgazhfaravhfh");
        MockHttpServletResponse response = mvc.perform(
                        MockMvcRequestBuilders.post("/login")
                                .content(jsonLogin.write(dto).getJson())
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}