package fr.auth.security;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class JwtSecurityConfigTest {

	@Bean
	@Primary
	public UserDetailsService userDetailsService() {

		var udm = new InMemoryUserDetailsManager();

		var admin = User.withUsername("admin").password("1234")
				.authorities("ADMIN").build();

		var manager = User.withUsername("manager").password("1234")
				.authorities("MANAGER").build();

		udm.createUser(admin);
		udm.createUser(manager);

		return udm;
	}
	


}
