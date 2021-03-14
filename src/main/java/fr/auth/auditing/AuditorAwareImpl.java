package fr.auth.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * 
 * @author hicham
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional <String> getCurrentAuditor() {
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication == null || !authentication.isAuthenticated()) {
	            return Optional.empty();
	        }
	       String email = (String) authentication.getPrincipal();
	       
	       return Optional.of(email);
	}

}
