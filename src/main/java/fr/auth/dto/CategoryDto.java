package fr.auth.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author hicham
 * class DTO
 */
@Data
@NoArgsConstructor @AllArgsConstructor @ToString
public class CategoryDto {
	
	private Integer id;
	private String name;
	private List<ProductDto> products;

}
