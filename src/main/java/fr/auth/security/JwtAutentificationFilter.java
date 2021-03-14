package fr.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.auth.constants.GlobalConstants;
import fr.auth.model.User;

/**
 * 
 * @author GUENDOUZI Hicham
 *
 */
public class JwtAutentificationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private JwtToken token;

	public JwtAutentificationFilter(AuthenticationManager authenticationManager, JwtToken token) {
		this.authenticationManager = authenticationManager;
		this.token = token;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		response.addHeader(GlobalConstants.TOKEN_HEADER, GlobalConstants.TOKEN_PREFIX + token.createToken(authResult));
	}

}
