package com.g3.elis.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.g3.elis.service.RoleService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitalizer {

	@Autowired
	private RoleService roleService;
	
	@PostConstruct
	public void init()throws Exception{
		roleService.initializeRoles(Arrays.asList("ADMIN","STUDENT","INSTRUCTOR"));
	}
}
