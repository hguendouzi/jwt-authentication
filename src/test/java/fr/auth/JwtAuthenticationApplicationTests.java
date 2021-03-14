package fr.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JwtAuthenticationApplication.class)
class JwtAuthenticationApplicationTests {
	
 

	@Test
	void contextLoads() {
		String context="ok";
		assertThat(context).isEqualTo("ok");
	}

}
