package fr.auth.utils;

import fr.auth.dto.CategoryDto;
import fr.auth.dto.ProductDto;
import fr.auth.dto.UserDto;
import fr.auth.enumeration.Role;
import fr.auth.model.Product;
import fr.auth.model.User;

import java.util.List;

/***
 * classe mock data 
 * @author hicham
 *
 */
public class MockData {

    /**
     * mock user
     *
     * @return user
     */
    public static User mockUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("test");
        user.setLastName("test");
        user.setPassword("paris1234");
        user.setEmail("test@test.fr");
        user.setRole(Role.ADMIN);
        return user;
    }

    /**
     * @return
     */
    public static UserDto mockUserDto() {
        UserDto user = new UserDto();
        user.setFirstName("test");
        user.setLastName("test");
        user.setPassword("paris1234");
        user.setEmail("test@test.fr");
        user.setRole(Role.ADMIN);
        return user;
    }


    public static CategoryDto mockCategoryDto() {
        CategoryDto dto = new CategoryDto();
        dto.setName("Fruits");
        dto.setProducts(List.of(mockProductDto()));
        return dto;
    }


    public static ProductDto mockProductDto() {
        ProductDto dto = new ProductDto();
        dto.setName("Banane");
        dto.setPrice(1.3);
        return dto;
    }

    public static Product mockProduct() {
        Product dto = new Product();
        dto.setName("Banane");
        dto.setPrice(1.3);
        return dto;
    }

}
