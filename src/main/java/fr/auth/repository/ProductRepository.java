package fr.auth.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.auth.model.Product;


/**
 * 
 * @author hicham
 *
 */
@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	Product findByName(String name);
	
	void deleteByName(String name);

}
