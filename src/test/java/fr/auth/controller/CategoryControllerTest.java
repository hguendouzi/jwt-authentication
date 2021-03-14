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

import fr.auth.dto.CategoryDto;
import fr.auth.security.JwtSecurityConfigTest;
import fr.auth.service.CategoryService;
import fr.auth.utils.MockData;
/**
 * 
 * @author hicham
 *
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = JwtSecurityConfigTest.class)
class CategoryControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@MockBean
	private CategoryService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private final MockData mockData = new MockData();
	
	protected JacksonTester<CategoryDto> jsonDto;

	@BeforeEach
	void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(springSecurity()).build();
		
		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	@DisplayName("Test operation save or update category")
	@WithUserDetails("admin")
	void should_be_return_200_with_category_when_call_operation_saveOrUpade() throws Exception {
		
		Mockito.when(service.saveOrUpdateCategory(mockData.mockCategoryDto())).thenReturn(mockData.mockCategoryDto());
        MockHttpServletResponse response = (MockHttpServletResponse) mvc.perform(
                MockMvcRequestBuilders.post("/category/create")
        		        .content(jsonDto.write(mockData.mockCategoryDto()).getJson())
                        .contentType(MediaType.APPLICATION_JSON))	 
        		        .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
	}
	
	
	@Test
	@DisplayName("Test operation find all category")
	@WithMockUser
	void should_be_return_200_with_list_of_category_when_call_operation_findAllCategory() throws Exception {
		
		Mockito.when(service.findAllCategory()).thenReturn(List.of(mockData.mockCategoryDto()));
        MockHttpServletResponse response = (MockHttpServletResponse) mvc.perform(
                MockMvcRequestBuilders.get("/category/all")
                        .contentType(MediaType.APPLICATION_JSON))
        		        .andExpect(jsonPath("$.[0].name", Matchers.is("Fruits")))
        		        .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
	}
	
	
	@Test
	@DisplayName("Test operation find category by name")
	@WithMockUser
	void should_be_return_200_category_found_when_call_operation_findByName() throws Exception {
		
		Mockito.when(service.findByName("Fruits")).thenReturn(mockData.mockCategoryDto());
        MockHttpServletResponse response = (MockHttpServletResponse) mvc.perform(
                MockMvcRequestBuilders.get("/category/Fruits")
                        .contentType(MediaType.APPLICATION_JSON))
        		        .andExpect(jsonPath("$.name", Matchers.is("Fruits")))
        		        .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
	}
	
	@Test
	@DisplayName("Test operation delete category by name")
	@WithUserDetails("admin")
	void should_be_return_200_with_rest_of_category_when_call_operation_deleteByName() throws Exception {
		
		Mockito.when(service.deleteByName("Fruits")).thenReturn(List.of(mockData.mockCategoryDto()));
        MockHttpServletResponse response = (MockHttpServletResponse) mvc.perform(
                MockMvcRequestBuilders.delete("/category/delete/Fruits")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.[0].name", Matchers.is("Fruits")))
		        .andReturn().getResponse();
assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
	}

}
