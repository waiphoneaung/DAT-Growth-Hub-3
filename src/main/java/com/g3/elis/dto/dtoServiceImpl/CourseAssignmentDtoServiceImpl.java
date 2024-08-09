package com.g3.elis.dto.dtoServiceImpl;

import org.springframework.stereotype.Service;

import com.g3.elis.dto.dtoService.CourseAssignmentDtoService;
import com.g3.elis.dto.form.CourseAssignmentDto;

@Service
public class CourseAssignmentDtoServiceImpl implements CourseAssignmentDtoService{

	@Override
	public CourseAssignmentDto createAssignmentDto(String assignmentTitle) {
		CourseAssignmentDto courseAssignmentDto = new CourseAssignmentDto();
		courseAssignmentDto.setTitle(assignmentTitle);
		return courseAssignmentDto;
	}

}
