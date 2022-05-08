package fr.auth.mapper;

import fr.auth.dto.ProductDto;
import fr.auth.model.Category;
import fr.auth.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * 
 * @author hicham
 *
 */

@Mapper(componentModel = "spring")
public interface ProductMapper {

	
	ProductDto toProductDto(Product product);
	
	@Mapping(source ="idCategory",target = "category", qualifiedByName = "id_category")
	Product toProduct(ProductDto productDto);

	List<ProductDto> toProductDtos(List<Product> products);

	List<Product> toProducts(List<ProductDto> productDtos);
	
	@Named("id_category")
	default Category getIdCategory(Integer id) {
		Category category=new Category();
		category.setId(id);
		return category;
	}

}
