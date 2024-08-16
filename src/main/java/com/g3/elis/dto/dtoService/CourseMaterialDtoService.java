package com.g3.elis.dto.dtoService;

import java.util.List;

import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.dto.form.CourseMaterialDto;

public interface CourseMaterialDtoService {
	CourseMaterialDto createMaterialDto(int index,String title,String content);
	List<CourseMaterialDto> editMaterialDto(int index,String title,String content,CourseCreationSuperDto superDto);
}
