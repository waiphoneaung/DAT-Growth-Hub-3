package com.g3.elis.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.g3.elis.model.Role;
import com.g3.elis.model.User;

public class LoginUserDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//need to implement User in model
	private final User user;

	public LoginUserDetail(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
	
//	 @Override
//	    public Collection<? extends GrantedAuthority> getAuthorities() {
//	        Set<Role> roles = user.getRoles();
//	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//	        for (Role role : roles) {
//	            // Prefix roles with ROLE_ if they are not already prefixed
//	            String roleName = role.getName().startsWith("ROLE_") ? role.getName() : "ROLE_" + role.getName();
//	            authorities.add(new SimpleGrantedAuthority(roleName));
//	        }
//	        return authorities;
//	    }


	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getStaffId();
	}

	public boolean isEnabled() {
		return user.isEnabled();
	}

	public User getUser() {
		return this.user;
	}
}