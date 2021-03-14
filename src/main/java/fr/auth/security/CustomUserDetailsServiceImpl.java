package fr.auth.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.auth.model.User;
import fr.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author GUENDOUZI Hicham
 *
 */
@Slf4j
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			log.error("User not found with username: [{}]", email);
			throw new UsernameNotFoundException("User not found with username : " + email);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
		return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), authorities);
	}

}
