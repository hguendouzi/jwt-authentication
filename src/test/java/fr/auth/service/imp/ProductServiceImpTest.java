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

import fr.auth.dto.ProductDto;
import fr.auth.exception.GlobalException;
import fr.auth.mapper.ProductMapper;
import fr.auth.model.Product;
import fr.auth.repository.ProductRepository;

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
	
	private final ProductMapper mapper=Mappers.getMapper(ProductMapper.class);
	
	private Product product;
	
	@BeforeEach
	void setUp() throws Exception {
		product=new Product();
		product.setName("coca");
		product.setPrice(1.5);
		
	}

	@Test
	@DisplayName("Test save or update product")
	void should_be_save_or_update_product() throws GlobalException {
		ProductDto dto = new ProductDto();
		dto.setName("coca");
		when(repository.save(mapper.toProduct(dto))).thenReturn(product);
		ProductDto response = service.saveOrUpdateProduct(dto);
		assertThat(response).isNotNull();
		
	}
	
	@Test
	@DisplayName("Test find all product")
	void should_be_find_all_product() throws GlobalException {
		when(repository.findAll()).thenReturn(List.of(product));
		List<ProductDto> list = service.findAll();
		assertThat(list).isNotEmpty();	
	}
	
	@Test
	@DisplayName("Test find by name product")
	void should_be_find_product_by_name() throws GlobalException {
		when(repository.findByName("coca")).thenReturn(product);
		ProductDto reponse = service.findByName("coca");;
		assertThat(mapper.toProduct(reponse)).isNotNull();	
	}

}
