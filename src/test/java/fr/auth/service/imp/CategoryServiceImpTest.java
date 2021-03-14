package fr.auth.service.imp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.auth.dto.CategoryDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.CategoryMapper;
import fr.auth.model.Category;
import fr.auth.model.Product;
import fr.auth.repository.CategoryRepository;

/***
 * 
 * @author hicham
 * test call methods of repository
 *
 */
@ExtendWith(SpringExtension.class)
class CategoryServiceImpTest {
	
	@InjectMocks
	private CategoryServiceImp service;
	@Mock
	private CategoryRepository repository;
	
	final private CategoryMapper mapper=Mappers.getMapper(CategoryMapper.class);
	
	private Category category;

	@BeforeEach
	void setUp() throws Exception {
		Product product=new Product();
		product.setName("coca");
		product.setPrice(1.5);
		category=new Category(1, "Fruits", List.of(product));
	}

	@Test
	@DisplayName("Test save or update catgeory")
	void should_be_save_or_update_category() throws GlobalException {
		CategoryDto dto=new CategoryDto();
		dto.setName("banane");
		when(repository.save(mapper.toCategory(dto))).thenReturn(category);
		CategoryDto response = service.saveOrUpdateCategory(dto);
		assertThat(response).isNotNull();
		assertThat(response.getId()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Test find all  catgeory")
	void should_be_find_all_category() throws GlobalException {
		when(repository.findAll()).thenReturn(List.of(category));
		List<CategoryDto> list = service.findAllCategory();
		assertThat(list).isNotEmpty();
	}
	
	@Test
	@DisplayName("Test find all  catgeory")
	void should_be_find_category_by_name() throws GlobalException {
		when(repository.findByName("Fruits")).thenReturn(category);
        CategoryDto dto = service.findByName("Fruits");  
		assertThat(dto).isNotNull();
		assertThat(dto.getId()).isEqualTo(1);
	}


}
