package fr.auth.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

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
			Claims claims = jwtToken.getClaimsFromToken(token);
			String email = claims.getSubject();
			@SuppressWarnings("unchecked")
			List<String> roles = (List<String>) claims.get("roles");
			Collection<? extends GrantedAuthority> authorities = roles.stream()
					.map(SimpleGrantedAuthority :: new  ).collect(Collectors.toList());
			UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(email, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(user);

		}
		filterChain.doFilter(request, response);
	}

}
