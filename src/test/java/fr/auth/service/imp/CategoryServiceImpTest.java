package fr.auth.service.imp;

import fr.auth.dto.CategoryDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.CategoryMapper;
import fr.auth.model.Category;
import fr.auth.model.Product;
import fr.auth.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
    @Mock
    private CategoryMapper mapper;


    private Product product = Product.builder().name("coca").price(1.5).build();
    private final Category category = Category.builder().id(1).name("Fruits").products(List.of(product)).build();

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    @DisplayName("Test save or update catgeory")
    void should_be_save_or_update_category() throws GlobalException {
        CategoryDto dto = CategoryDto.builder().id(1).build();
        when(mapper.toCategory(Mockito.any(CategoryDto.class))).thenReturn(category);
        when(repository.save(category)).thenReturn(category);
        when(mapper.toCategoryDto(category)).thenReturn(dto);
        CategoryDto response = service.saveOrUpdateCategory(dto);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test find all  catgeory")
    void should_be_find_all_category() throws GlobalException {
        CategoryDto dto = CategoryDto.builder().id(1).build();
        when(repository.findAll()).thenReturn(List.of(category));
        when(mapper.toListDto(Mockito.anyList())).thenReturn(List.of(dto));
        List<CategoryDto> list = service.findAllCategory();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("Test find all  catgeory")
    void should_be_find_category_by_name() throws GlobalException {
        CategoryDto dto = CategoryDto.builder().id(1).build();
        when(repository.findByName("Fruits")).thenReturn(category);
        when(mapper.toCategoryDto(category)).thenReturn(dto);
        CategoryDto response = service.findByName("Fruits");
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1);
    }


}
