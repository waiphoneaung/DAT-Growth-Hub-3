package com.g3.elis.controller.admin;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.g3.elis.model.CourseCategory;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseCategoryService;
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
	
	@Autowired
	private CourseCategoryService courseCategoryService;
	
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
	public String adminCreateCourse(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
									@RequestParam(name = "courseTitle",required=false)String courseTitle,
									@RequestParam(name = "courseDescription",required=false)String courseDescription,
									@RequestParam(name = "durationHour",required=false)Integer durationHour,
									@RequestParam(name = "courseInfo",required=false)String courseInfo,
									@RequestParam(name = "image",required=false)MultipartFile imgFile,
									Model model) 
	{
		List<CourseCategory> courseCategoryList = courseCategoryService.getAllCourseCategories();
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
			MultipartFile imageFile = imgFile;
			model.addAttribute("superDto",newSuperDto);
			model.addAttribute("imgFile",imageFile);
			model.addAttribute("courseCategories",courseCategoryList);
			
			return "/admin/admin-create-course";
		}
		superDto.getCourseDto().setCourseTitle(courseTitle);
		superDto.getCourseDto().setCourseDescription(courseDescription);
		if(durationHour != null) superDto.getCourseDto().setDurationHour(durationHour);
		else superDto.getCourseDto().setDurationHour(0);
		superDto.getCourseDto().setCourseInfo(courseInfo);
		MultipartFile imageFile = imgFile;
		model.addAttribute("superDto",superDto);
		model.addAttribute("courseCategories",courseCategoryList);
		model.addAttribute("imgFile",imageFile);
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
									@RequestParam(name = "module-title",required=false)String title,
									@RequestParam(name = "courseTitle",required=false)String courseTitle,
									@RequestParam(name = "courseDescription",required=false)String courseDescription,
									@RequestParam(name = "durationHour",required=false)Integer durationHour,
									@RequestParam(name = "courseInfo",required=false)String courseInfo,
									@RequestParam(name = "image",required=false)MultipartFile imgFile,
									Model model)
	{
		List<CourseCategory> courseCategoryList = courseCategoryService.getAllCourseCategories();
		superDto.getCourseModuleDtoList().add(courseModuleDtoService.createModuleDtoWithTitle(title));
		superDto.getCourseDto().setCourseTitle(courseTitle);
		superDto.getCourseDto().setCourseDescription(courseDescription);
		if(durationHour != null) superDto.getCourseDto().setDurationHour(durationHour);
		else superDto.getCourseDto().setDurationHour(0);
		superDto.getCourseDto().setCourseInfo(courseInfo);
		MultipartFile imageFile = imgFile;
		model.addAttribute("courseCategories",courseCategoryList);
		model.addAttribute("imgFile",imageFile);
		model.addAttribute("superDto",superDto);
		return "/admin/admin-create-course";
	}
	@PostMapping("/admin-create-course/add-material")
	public String adminCreateMaterial(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
									  @RequestParam(name = "courseModuleIndex",required=false)int index,
									  @RequestParam(name = "title")String title,
									  @RequestParam(name = "video",required=false)String video,
									  @RequestParam(name = "content") String content,
									  @RequestParam(name = "action") String action,
									  @RequestParam(name = "courseTitle",required=false)String courseTitle,
									  @RequestParam(name = "courseDescription",required=false)String courseDescription,
									  @RequestParam(name = "durationHour",required=false)Integer durationHour,
									  @RequestParam(name = "courseInfo",required=false)String courseInfo,
									  @RequestParam(name = "image",required=false)MultipartFile imgFile,
									  Model model)
	{
		if(action.equalsIgnoreCase("add"))
		{
			superDto.getCourseMaterialDtoList().add(courseMaterialDtoService.createMaterialDto(index,title,content));
		}
		else if(action.equalsIgnoreCase("edit"))
		{
			superDto.setCourseMaterialDtoList(courseMaterialDtoService.editMaterialDto(index, title, content, superDto));
		}
		
		List<CourseCategory> courseCategoryList = courseCategoryService.getAllCourseCategories();
		superDto.getCourseDto().setCourseTitle(courseTitle);
		superDto.getCourseDto().setCourseDescription(courseDescription);
		if(durationHour != null) superDto.getCourseDto().setDurationHour(durationHour);
		else superDto.getCourseDto().setDurationHour(0);
		superDto.getCourseDto().setCourseInfo(courseInfo);
		MultipartFile imageFile = imgFile;
		model.addAttribute("courseCategories",courseCategoryList);
		model.addAttribute("superDto",superDto);
		model.addAttribute("imgFile",imageFile);
		return "redirect:/admin/admin-create-course";
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

