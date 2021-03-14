/**
 * 
 */
package fr.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.auth.enumeration.Role;
import fr.auth.model.User;

/**
 * @author hicham
 *
 */
@DataJpaTest(showSql = false)
class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	private User user1;
	private User user2;

	@BeforeEach
	void setUp() {
		user1 = new User();
		user1.setLastName("testAdmin");
		user1.setFirstName("testAdmin");
		user1.setEmail("testAdmin@paris.fr");
		user1.setPassword("password123");
		user1.setRole(Role.ADMIN);

		user2 = new User();
		user2.setLastName("testManger");
		user2.setFirstName("testManger");
		user2.setEmail("testmanager@paris.fr");
		user2.setPassword("password123");
		user2.setRole(Role.MANGER);

	}

	@Test
	@DisplayName("test save user ")
	void should_be_save_user() {
		User user = repository.save(user1);
		assertThat(user).isNotNull();

	}

	@Test
	@DisplayName("test find user by login")
	void should_be_find_user_by_login() {
		repository.save(user2);
		User userFind = repository.findByEmail("testmanager@paris.fr");
		assertThat(userFind).isNotNull();
		assertThat(userFind.getEmail()).isEqualTo("testmanager@paris.fr");

	}

	@Test
	@DisplayName("test delete user by login")
	void should_be_delete_user_by_login() {
		repository.save(user2);
		repository.deleteByEmail("testmanager@paris.fr");
		User userFind = repository.findByEmail("testmanager@paris.fr");
		assertThat(userFind).isNull();

	}

}
