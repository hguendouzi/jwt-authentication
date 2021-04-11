package fr.auth.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hicham
 *
 */
@Component
public class CustomLogoutHandler implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		SecurityContextHolder.getContext().getAuthentication();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

}
