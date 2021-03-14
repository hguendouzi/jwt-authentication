package fr.auth.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.auth.model.Category;


/**
 * 
 * @author hicham
 *
 */
@Repository
@Transactional
public interface CategoryRepository extends CrudRepository<Category ,Integer> {
	
	Category findByName(String name);
  
	void deleteByName(String name);

}
