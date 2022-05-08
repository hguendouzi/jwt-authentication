package fr.auth.service.imp;

import fr.auth.dto.LoginDto;
import fr.auth.exception.GlobalException;
import fr.auth.security.JwtToken;
import fr.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * 
 * @author hicham
 *
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	

	private final AuthenticationManager authenticationManager;
	private final JwtToken token;

	@Override
	public String login(LoginDto loginDto) throws GlobalException {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return token.createToken(authentication);
		} catch (BadCredentialsException e) {
			throw new GlobalException("incorrect username or password");
		}
		
	}

}
