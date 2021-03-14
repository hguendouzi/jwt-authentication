package fr.auth.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.auth.model.User;

/***
 * 
 * @author hicham
 *
 */

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {
	
      User findByEmail(String email);
	
      void deleteByEmail(String email);

}
