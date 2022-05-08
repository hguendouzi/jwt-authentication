package fr.auth.service.imp;

import fr.auth.dto.ProductDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.ProductMapper;
import fr.auth.repository.ProductRepository;
import fr.auth.utils.MockData;
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
 *
 */
@ExtendWith(SpringExtension.class)
class ProductServiceImpTest {
    
	@InjectMocks
	private ProductServiceImp service;
	@Mock
	private ProductRepository repository;
	@Mock
	private  ProductMapper mapper;

	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@DisplayName("Test save or update product")
	void should_be_save_or_update_product() throws GlobalException {
		when(mapper.toProduct(Mockito.any(ProductDto.class))).thenReturn(MockData.mockProduct());
		when(mapper.toProductDto(MockData.mockProduct())).thenReturn(MockData.mockProductDto());
		when(repository.save(MockData.mockProduct())).thenReturn(MockData.mockProduct());
		ProductDto response = service.saveOrUpdateProduct(MockData.mockProductDto());
		assertThat(response).isNotNull();
		
	}
	
	@Test
	@DisplayName("Test find all product")
	void should_be_find_all_product() throws GlobalException {
		when(mapper.toProductDtos(Mockito.anyList())).thenReturn(List.of(MockData.mockProductDto()));
		when(repository.findAll()).thenReturn(List.of(MockData.mockProduct()));
		List<ProductDto> list = service.findAll();
		assertThat(list).isNotEmpty();	
	}
	
	@Test
	@DisplayName("Test find by name product")
	void should_be_find_product_by_name() throws GlobalException {
		when(mapper.toProductDto(MockData.mockProduct())).thenReturn(MockData.mockProductDto());
		when(repository.findByName("coca")).thenReturn(MockData.mockProduct());
		ProductDto response = service.findByName("coca");;
		assertThat(response).isNotNull();
	}

}
