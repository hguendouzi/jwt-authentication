package fr.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * @author GUENDOUZI Hicham
 *
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private JwtToken jwtToken;

	public JWTAuthorizationFilter(JwtToken jwtToken) {
		this.jwtToken = jwtToken;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = jwtToken.getJwtFromRequest(request);
		if (StringUtils.isNoneBlank(token) && jwtToken.validateToken(token)) {
			SecurityContextHolder.getContext().getAuthentication();

		}
		filterChain.doFilter(request, response);
	}
	

}
