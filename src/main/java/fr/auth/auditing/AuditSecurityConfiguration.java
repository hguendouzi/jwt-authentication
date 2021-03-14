package fr.auth.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import fr.auth.repository.UserRepository;

/**
 * 
 * @author hicham
 *
 */
@Configuration
@EnableJpaAuditing
public class AuditSecurityConfiguration {
	 @Bean
	  AuditorAware<String> auditorAware(UserRepository repository) {
	 return new AuditorAwareImpl();
	    
	  }

}
