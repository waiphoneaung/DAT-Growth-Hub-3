package com.g3.elis.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.form.UserDto;
import com.g3.elis.model.Role;
import com.g3.elis.model.User;
import com.g3.elis.repository.RoleRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void createUser(UserDto userDto) {
		User user = new User();
		
		if(userDto.getEmail() == null)
		{
			user.setEmail(userDto.getName().toLowerCase().replaceAll("\\s+", "") + "@diracetechnology.com");
		}
		else
		{
			user.setEmail(userDto.getEmail());
		}
		
		user.setName(userDto.getName());
		user.setDivision(userDto.getDivision());
		user.setStaffId(userDto.getStaffId());
		user.setDoorLogNo(userDto.getDoorLogNo());
		user.setDept(userDto.getDept());
		user.setTeam(userDto.getTeam());
		user.setStatus(userDto.getStatus());
		
		user.setEnabled(true);
		if(userDto.getPassword()!=null)
		{
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		else
		{ 	
			user.setPassword(passwordEncoder.encode("dirace@1234"));
		}
		
//		Role userRole = roleRepository.findById(2).orElseThrow(() -> new RuntimeException("Role is not found!"));
		Role userRole = roleRepository.findByName("STUDENT").orElseThrow(() -> new RuntimeException("Role is not found!"));
		
		user.getRoles().add(userRole);
		userRole.getUsers().add(user);
		
		userRepository.save(user);
	}

}
