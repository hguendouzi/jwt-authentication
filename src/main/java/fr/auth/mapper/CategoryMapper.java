package fr.auth.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import fr.auth.dto.CategoryDto;
import fr.auth.model.Category;

/**
 * 
 * @author hicham
 *
 */
@Mapper
public interface CategoryMapper {
	

	CategoryDto toCategoryDto(Category category);
	Category toCategory(CategoryDto dto);
	List<CategoryDto> toListDto(List<Category> list);
	List<Category> toList(List<CategoryDto> list);

}
