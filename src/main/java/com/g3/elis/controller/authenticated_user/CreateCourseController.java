package com.g3.elis.controller.authenticated_user;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.dtoService.CourseAssignmentDtoService;
import com.g3.elis.dto.dtoService.CourseMaterialDtoService;
import com.g3.elis.dto.dtoService.CourseModuleDtoService;
import com.g3.elis.dto.form.CourseAssignmentDto;
import com.g3.elis.dto.form.CourseCreationSuperDto;
import com.g3.elis.dto.form.CourseDto;
import com.g3.elis.dto.form.CourseMaterialDto;
import com.g3.elis.dto.form.CourseModuleDto;
import com.g3.elis.dto.form.InputFileDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseCategory;
import com.g3.elis.model.CourseMaterial;
import com.g3.elis.model.CourseModule;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseCategoryService;

import com.g3.elis.model.BlogPost;
import com.g3.elis.model.Course;

import com.g3.elis.model.CourseMaterial;
import com.g3.elis.model.CourseModule;
import com.g3.elis.service.CourseMaterialService;
import com.g3.elis.service.CourseModuleService;
import com.g3.elis.service.CourseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping({"/admin","/instructor"})
@SessionAttributes("superDto")
public class CreateCourseController 
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

	private CourseModuleService courseModuleService;
	
	@Autowired
	private CourseMaterialService courseMaterialService;
	
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
        superDto.setAction(null);
        superDto.setCourseId(0);
        return superDto;
    }
	
	@GetMapping("/create-course")
	public String adminCreateCourse(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
									@RequestParam(name = "courseTitle",required=false)String courseTitle,
									@RequestParam(name = "courseDescription",required=false)String courseDescription,
									@RequestParam(name = "durationHour",required=false)Integer durationHour,
									@RequestParam(name = "courseInfo",required=false)String courseInfo,
									@RequestParam(name = "image",required=false)MultipartFile imgFile,
									@RequestParam(name = "action")String action, BindingResult bindingResult,
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
			newSuperDto.setAction(action);

			MultipartFile imageFile = imgFile;
			model.addAttribute("superDto",newSuperDto);
			model.addAttribute("imgFile",imageFile);
			model.addAttribute("courseCategories",courseCategoryList);
			model.addAttribute("map",determineMapping());
			return "/authenticated-user/admin-create-course";
		}
		superDto.getCourseDto().setCourseTitle(courseTitle);
		superDto.getCourseDto().setCourseDescription(courseDescription);
		if(durationHour != null) superDto.getCourseDto().setDurationHour(durationHour);
		else superDto.getCourseDto().setDurationHour(0);
		superDto.getCourseDto().setCourseInfo(courseInfo);

		superDto.setAction(action);
		MultipartFile imageFile = imgFile;
		model.addAttribute("action",superDto.getAction());
		model.addAttribute("superDto",superDto);
		model.addAttribute("courseCategories",courseCategoryList);
		model.addAttribute("imgFile",imageFile);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/create-course";
	}
	
	@GetMapping("/create-course/{courseId}/{action}")
	public String adminEditCourse(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
									@RequestParam(name = "courseTitle",required=false)String courseTitle,
									@RequestParam(name = "courseDescription",required=false)String courseDescription,
									@RequestParam(name = "durationHour",required=false)Integer durationHour,
									@RequestParam(name = "courseInfo",required=false)String courseInfo,
									@RequestParam(name = "image",required=false)MultipartFile imgFile,
									@PathVariable(name = "action")String action,
									@PathVariable(name = "courseId")Integer courseId,
									Model model) 
	{
		List<CourseCategory> courseCategoryList = courseCategoryService.getAllCourseCategories();
		
		CourseCreationSuperDto newSuperDto = new CourseCreationSuperDto();
		newSuperDto.setCourseDto(new CourseDto());
		newSuperDto.setInputFileDto(new InputFileDto());
		newSuperDto.setCourseMaterialDtoList(new ArrayList<CourseMaterialDto>());
		newSuperDto.setCourseAssignmentDtoList(new ArrayList<CourseAssignmentDto>());
		newSuperDto.setCourseModuleDtoList(new ArrayList<CourseModuleDto>());
		newSuperDto.setCourseId(courseId);
		
		Course course = courseService.getCourseById(courseId);
		
		newSuperDto.getCourseDto().setCourseDescription(course.getCourseDescription());
		newSuperDto.getCourseDto().setCourseInfo(course.getCourseInfo());
		newSuperDto.getCourseDto().setCourseTitle(course.getCourseTitle());
		newSuperDto.getCourseDto().setDurationHour(course.getDuration());
		newSuperDto.getCourseDto().setCourseImageFileName(course.getCourseImageFileName());
		List<CourseModule> courseModuleList = courseModuleService.getAllCourseModuleByCourseId(courseId);
		int index = 0;
		for(CourseModule courseModule : courseModuleList)
		{
			CourseModuleDto courseModuleDto = new CourseModuleDto();
			courseModuleDto.setModuleTitle(courseModule.getModuleTitle());
			newSuperDto.getCourseModuleDtoList().add(courseModuleDto);
			List<CourseMaterial> courseMaterialList = courseMaterialService.getAllCourseMaterialByCourseModuleId(courseModule.getId());
			for(CourseMaterial courseMaterial : courseMaterialList)
			{
				CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
				courseMaterialDto.setIndex(index);
				courseMaterialDto.setContent(courseMaterial.getContent());
				courseMaterialDto.setTitle(courseMaterial.getTitle());
				newSuperDto.getCourseMaterialDtoList().add(courseMaterialDto);
			}
			index++;
		}
		MultipartFile imageFile = imgFile;
		newSuperDto.setAction(action);
		model.addAttribute("action",superDto.getAction());
		model.addAttribute("superDto",newSuperDto);
		model.addAttribute("courseCategories",courseCategoryList);
		model.addAttribute("imgFile",imageFile);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/create-course";
	}
	@PostMapping("/create-course")
	public String createCourse(@ModelAttribute("superDto")CourseCreationSuperDto superDto,
							   @RequestParam(name = "imgFile",required=false)MultipartFile imgFile,
							   @RequestParam(name = "courseCategoryId")int courseCategoryId,
							   Authentication authentication,Model model) throws IOException
	{
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		System.out.print(superDto.getAction());
		if(superDto.getAction().equals("add"))
		{
			courseService.createCourse(superDto,user,imgFile,courseCategoryId);
		}
		else
		{
			superDto.getAction().equals("edit");
			courseService.editCourse(superDto, user,imgFile,courseCategoryId,superDto.getCourseId());
		}
		model.addAttribute("map",determineMapping());
		return "redirect:/" + determineMapping() +"/"+determineMapping()+"-course-list";
	}
	@PostMapping("/create-course/add-module")
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
		model.addAttribute("action",superDto.getAction());
		model.addAttribute("courseCategories",courseCategoryList);
		model.addAttribute("imgFile",imageFile);
		model.addAttribute("superDto",superDto);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/create-course";
	}
	@PostMapping("/create-course/add-material")
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
		if(superDto.getAction().equalsIgnoreCase("add"))
		{
			superDto.getCourseMaterialDtoList().add(courseMaterialDtoService.createMaterialDto(index,title,content));
		}
		else if(superDto.getAction().equalsIgnoreCase("edit"))
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
		model.addAttribute("action",superDto.getAction());
		model.addAttribute("courseCategories",courseCategoryList);
		model.addAttribute("superDto",superDto);
		model.addAttribute("imgFile",imageFile);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/create-course";

	}
	
	private String determineMapping()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		if(userDetail.isAdmin())
		{
			return "admin";
		}
		return "instructor";
	}
}

