package fr.auth.security;

import fr.auth.constants.GlobalConstants;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author GUENDOUZI Hicham
 *
 */
@Slf4j
@Component
public class JwtToken {
    
	/**
	 * create an token 
	 * @param authent
	 * @return token
	 */
	public String createToken(Authentication authent) {
		UserPrincipal user = (UserPrincipal) authent.getPrincipal();
		user.setAuthorities(authent.getAuthorities());
		return GlobalConstants.TOKEN_PREFIX
				+ Jwts.builder().setSubject(user.getEmail()).claim("roles", getRoles(user.getAuthorities()))
						.setExpiration(new Date(System.currentTimeMillis() + GlobalConstants.EXPIRATION))
						.signWith(SignatureAlgorithm.HS512, GlobalConstants.SECRET).compact();

	}
    
	/**
	 * validation token
	 * @param authToken
	 * @return boolean true if token valid 
	 */
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(GlobalConstants.SECRET).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty");
		}
		return false;
	}

	public Claims getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(GlobalConstants.SECRET).parseClaimsJws(token).getBody();
	}

	public String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(GlobalConstants.TOKEN_HEADER);
		if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(GlobalConstants.TOKEN_PREFIX)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	private List<String> getRoles(Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}

}
