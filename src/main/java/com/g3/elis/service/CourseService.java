package com.g3.elis.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.model.User;


public interface CourseService {
	void createCourse(CourseCreationSuperDto superDto,User user,MultipartFile imgFile)  throws IOException;
}
