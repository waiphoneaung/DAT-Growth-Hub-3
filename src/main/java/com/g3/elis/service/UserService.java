package com.g3.elis.service;

import com.g3.elis.dto.form.UserDto;
import com.g3.elis.model.User;

public interface UserService {
	User getUserById(int id);
	void createUser(UserDto userDto);
}
