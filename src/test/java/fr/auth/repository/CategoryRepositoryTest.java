package fr.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.auth.model.Category;
import fr.auth.model.Product;

/***
 * test interface Category repository
 * 
 * @author hicham
 *
 */
@DataJpaTest(showSql = true)
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;
	@Autowired
	private ProductRepository productRepository;

	@Test
	@DisplayName("test save category")
	void should_be_save_category() {
		Category category = new Category();
		category.setName("Fruits and Vegetables Test");
		List<Product> products = List.of(new Product(null, "bananaTest", 1.50, null));
		category.setProducts(products);
		Category catg = repository.save(category);
		assertThat(catg).isNotNull();
	}

	@Test
	@DisplayName("test find category by name")
	void should_be_find_category_name() {
		Category catg = new Category();
		catg.setName("Test Test");
		repository.save(catg);
		Category catg_ = repository.findByName("Test Test");
		assertThat(catg_).isNotNull();
	}

	@Test
	@DisplayName("test delete category by name")
	void should_be_delete_category_name() {
		Category category2 = new Category();
		category2.setName("Boissons Test");
		repository.save(category2);
		repository.deleteByName("Boissons Test");
		Category catg = repository.findByName("Boissons Test");
		assertThat(catg).isNull();
	}
	
	@Test
	@DisplayName("test delete category by name and product")
	void when_delete_category_should_be_delete_all_product_attached() {
		
		Category category3 = new Category();
		category3.setName("Category Test");
		
		Product product1 = new Product();
		product1.setName("coca");
		product1.setPrice(2.5);

		Product product2 = new Product();
		product2.setName("water");
		product2.setPrice(2.5);
		
		List<Product> list=List.of(product1,product2);
		category3.setProducts(list);
	    repository.save(category3);
		
		
		repository.deleteByName("Category Test");
		
		Product prodCoca = productRepository.findByName("coca");
		Product prodWater = productRepository.findByName("water");
	
		Category catgDelete = repository.findByName("Category Test");
		
		productRepository.findAll();
		assertThat(catgDelete).isNull();
		assertThat(prodCoca).isNull();
		assertThat(prodWater).isNull();
	}
	
	
	
}
