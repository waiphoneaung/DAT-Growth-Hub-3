package com.g3.elis.dto.dtoServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g3.elis.dto.dtoService.CourseMaterialDtoService;
import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.dto.form.CourseMaterialDto;

@Service
public class CourseMaterialDtoServiceImpl implements CourseMaterialDtoService{

	@Override
	public CourseMaterialDto createMaterialDto(int index, String title,String content) {
		CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
		courseMaterialDto.setContent(content);
		courseMaterialDto.setIndex(index);
		courseMaterialDto.setTitle(title);
		return courseMaterialDto;
	}

	@Override
	public List<CourseMaterialDto> editMaterialDto(int index, String title, String content,CourseCreationSuperDto superDto) {
		for(CourseMaterialDto courseMaterialDto : superDto.getCourseMaterialDtoList())
		{
			if(courseMaterialDto.getIndex() == index)
			{
				courseMaterialDto.setContent(content);
				courseMaterialDto.setTitle(title);
			}
		}
		return superDto.getCourseMaterialDtoList();
	}



}
