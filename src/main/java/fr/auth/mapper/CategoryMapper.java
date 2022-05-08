package fr.auth.mapper;

import fr.auth.dto.CategoryDto;
import fr.auth.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 
 * @author hicham
 *
 */
@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CategoryMapper {
	

	CategoryDto toCategoryDto(Category category);
	Category toCategory(CategoryDto dto);
	List<CategoryDto> toListDto(List<Category> list);
	List<Category> toList(List<CategoryDto> list);

}
