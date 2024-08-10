package com.g3.elis.controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.dtoService.CourseAssignmentDtoService;
import com.g3.elis.dto.dtoService.CourseMaterialDtoService;
import com.g3.elis.dto.dtoService.CourseModuleDtoService;
import com.g3.elis.dto.form.AnswerDto;
import com.g3.elis.dto.form.CourseAssignmentDto;
import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.dto.form.CourseDto;
import com.g3.elis.dto.form.CourseMaterialDto;
import com.g3.elis.dto.form.CourseModuleDto;
import com.g3.elis.dto.form.InputFileDto;
import com.g3.elis.dto.form.QuestionDto;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("superDto")
public class AdminCreateCourseController 
{
	@Autowired
	private CourseModuleDtoService courseModuleDtoService;
	
	@Autowired
	private CourseMaterialDtoService courseMaterialDtoService;
	
	@Autowired
	private CourseAssignmentDtoService courseAssignmentDtoService;
	
	@Autowired
	private CourseService courseService;
	
	@ModelAttribute("superDto")
    public CourseCreationSuperDto populateSuperDto() {
        CourseCreationSuperDto superDto = new CourseCreationSuperDto();
        superDto.setCourseDto(new CourseDto());
        superDto.setInputFileDto(new InputFileDto());
        superDto.setCourseMaterialDtoList(new ArrayList<CourseMaterialDto>());
        superDto.setCourseAssignmentDtoList(new ArrayList<CourseAssignmentDto>());
        superDto.setCourseModuleDtoList(new ArrayList<CourseModuleDto>());
        superDto.setQuestionDtoList(new ArrayList<QuestionDto>());
        superDto.setAnswerDtoList(new ArrayList<AnswerDto>());
        return superDto;
    }
	
	@GetMapping("/admin-create-course")
	public String adminCreateCourse(@ModelAttribute("superDto")CourseCreationSuperDto superDto,Model model) 
	{
		if(superDto == null)
		{
			CourseCreationSuperDto newSuperDto = new CourseCreationSuperDto();
			newSuperDto.setCourseDto(new CourseDto());
			newSuperDto.setInputFileDto(new InputFileDto());
			newSuperDto.setCourseMaterialDtoList(new ArrayList<CourseMaterialDto>());
			newSuperDto.setCourseAssignmentDtoList(new ArrayList<CourseAssignmentDto>());
			newSuperDto.setCourseModuleDtoList(new ArrayList<CourseModuleDto>());
			newSuperDto.setQuestionDtoList(new ArrayList<QuestionDto>());
			newSuperDto.setAnswerDtoList(new ArrayList<AnswerDto>());
			model.addAttribute("superDto",newSuperDto);
			return "/admin/admin-create-course";
		}
		model.addAttribute("superDto",superDto);
		return "/admin/admin-create-course";
	}
	@PostMapping("/admin-create-course")
	public String createCourse(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
							   @RequestParam(name = "imgFile",required=false)MultipartFile imgFile,
							   Authentication authentication,Model model) throws IOException
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		courseService.createCourse(superDto,user,imgFile);
		model.addAttribute("content","admin/admin-course-list");
		return "/admin/admin-layout";
	}
	@PostMapping("/admin-create-course/add-module")
	public String adminCreateModule(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
									@RequestParam(name = "module-title",required=false)String title,Model model)
	{
		superDto.getCourseModuleDtoList().add(courseModuleDtoService.createModuleDtoWithTitle(title));
		model.addAttribute("superDto",superDto);
		return "/admin/admin-create-course";
	}
	@PostMapping("/admin-create-course/add-material")
	public String adminCreateMaterial(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
									  @RequestParam(name = "courseModuleIndex",required=false)int index,
									  @RequestParam(name = "title")String title,
									  @RequestParam(name = "video",required=false)String video,
									  @RequestParam(name = "content") String content,
									  Model model)
	{
		superDto.getCourseMaterialDtoList().add(courseMaterialDtoService.createMaterialDto(index,title,content));
		model.addAttribute("superDto",superDto);
		return "/admin/admin-create-course";
	}
	@GetMapping("/admin-create-course/admin-quiz")
	public String adminCreateQuiz(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
								  @RequestParam("courseModuleId")int moduleId,
								  @RequestParam(name = "assignment-title",required = false)String assignmentTitle,
					      	 	  Model model)
	{
		superDto.getCourseAssignmentDtoList().add(courseAssignmentDtoService.createAssignmentDto(assignmentTitle));
		model.addAttribute("moduleId",moduleId);
		model.addAttribute("superDto",superDto);	
		return "/admin/admin-quiz";
	}

}
