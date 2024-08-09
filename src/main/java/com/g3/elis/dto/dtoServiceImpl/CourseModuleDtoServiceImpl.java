package com.g3.elis.dto.dtoServiceImpl;

import org.springframework.stereotype.Service;

import com.g3.elis.dto.dtoService.CourseModuleDtoService;
import com.g3.elis.dto.form.CourseModuleDto;

@Service
public class CourseModuleDtoServiceImpl implements CourseModuleDtoService{

	@Override
	public CourseModuleDto createModuleDtoWithTitle(String title) {
		CourseModuleDto courseModuleDto = new CourseModuleDto();
		courseModuleDto.setModuleTitle(title);
		return courseModuleDto;
	}
}
