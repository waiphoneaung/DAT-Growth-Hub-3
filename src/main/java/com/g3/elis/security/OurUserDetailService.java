package com.g3.elis.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.g3.elis.model.User;
import com.g3.elis.repository.UserRepository;

@Service
public class OurUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String staffId) throws UsernameNotFoundException {
		
		//Need to implement Find by staff Id Method in Uesr Repository
		Optional<User> user = userRepository.findByStaffId(staffId);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("user not found ");
		}

		return new LoginUserDetail(user.get());
	}
}

