package fr.auth.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.auth.dto.LoginDto;
import fr.auth.exception.GlobalException;
import fr.auth.security.JwtToken;
import fr.auth.service.LoginService;


/**
 * 
 * @author hicham
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtToken token;

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
