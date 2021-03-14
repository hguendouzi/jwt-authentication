package fr.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author hicham
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
	    private Integer id;
		private String name;
		private double price;
		private Integer idCategory;


}
