package com.g3.elis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.Role;
import com.g3.elis.repository.RoleRepository;

@Service
public interface RoleService {

	
	void initializeRoles(List<String>roles) throws Exception;
	Role getRoleByName(String name);

	Role getRoleByUserId(int userId);

	public Role findById(int id);
    
    public Optional<Role> findByName(String roleName);
}
