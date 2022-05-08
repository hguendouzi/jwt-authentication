package fr.auth.dto;

import lombok.*;

import java.util.List;

/**
 * 
 * @author hicham
 * class DTO
 */
@Data
@NoArgsConstructor @AllArgsConstructor @ToString
@Builder
public class CategoryDto {
	
	private Integer id;
	private String name;
	private List<ProductDto> products;

}
