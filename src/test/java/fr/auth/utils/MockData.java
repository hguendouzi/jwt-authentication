package fr.auth.utils;

import java.util.List;

import fr.auth.dto.CategoryDto;
import fr.auth.dto.ProductDto;
import fr.auth.dto.UserDto;
import fr.auth.enumeration.Role;
import fr.auth.model.User;

/***
 * classe mock data 
 * @author hicham
 *
 */
public class MockData {
	
	/**
	 * mock user 
	 * @return user
	 */
	public User mockUser() {
	    User user=new User();
	    user.setId( 1);
		user.setFirstName("test");
		user.setLastName("test");
		user.setPassword("paris1234");
		user.setEmail("test@test.fr");
		user.setRole(Role.ADMIN);
		return user;
}   
	/**
	 * 
	 * @return
	 */
	public UserDto mockUserDto() {
		UserDto user=new UserDto();
		user.setFirstName("test");
		user.setLastName("test");
		user.setPassword("paris1234");
		user.setEmail("test@test.fr");
		user.setRole(Role.ADMIN);
		return user;
}
	
	
	public CategoryDto mockCategoryDto() {
		CategoryDto dto=new CategoryDto();
		dto.setName("Fruits");
		dto.setProducts(List.of(mockProductDto()));
		return dto;
	}
	
	
	public ProductDto mockProductDto() {
		ProductDto dto =new ProductDto();
		dto.setName("Banane");
		dto.setPrice(1.3);
		return dto;
	}

}
