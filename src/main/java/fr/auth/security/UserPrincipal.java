package fr.auth.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.auth.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 
 * @author hicham
 *
 */
@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

	/** serialVersionUID */
	private static final long serialVersionUID = -3031215143944717255L;

	private Integer id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public static UserPrincipal createUser(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
		return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
