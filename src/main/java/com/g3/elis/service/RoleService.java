package com.g3.elis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g3.elis.model.Role;

@Service
public interface RoleService {

	void initializeRoles(List<String>roles) throws Exception;
	Role getRoleByName(String name);

	Role getRoleByUserId(int userId);

}
