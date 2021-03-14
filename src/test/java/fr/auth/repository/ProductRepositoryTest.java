package fr.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.auth.model.Category;
import fr.auth.model.Product;

/***
 * 
 * @author hicham
 *
 */
@DataJpaTest(showSql = false)
class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;
	private Product product1;
	private Product product2;

	@BeforeEach
	void setUp() {
		product1 = new Product();
		product1.setName("coca");
		product1.setPrice(2.5);

		product2 = new Product();
		product2.setName("water");
		product2.setPrice(2.5);

	}

	@Test
	@DisplayName("test save product")
	void should_be_save_product_and_attached_with_category() {
		Category categ = categoryRepository.findByName("Boissons");
		product1.setCategory(categ);
		Product prodFind = repository.save(product1);
		assertThat(prodFind).isNotNull();
		assertThat(prodFind.getCategory().getId()).isNotZero();
		assertThat(prodFind.getCategory().getId()).isEqualTo(3);

	}

	@Test
	@DisplayName("test find product by name")
	void should_be_find_product_by_name() {
		Category categ = categoryRepository.findByName("Boissons");
		product2.setCategory(categ);
		repository.save(product2);
		Product product = repository.findByName("water");
		assertThat(product).isNotNull();
	}

}
