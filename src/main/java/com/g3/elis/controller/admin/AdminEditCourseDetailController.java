package com.g3.elis.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.dto.form.CourseMaterialDto;
import com.g3.elis.dto.form.CourseModuleDto;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseMaterial;
import com.g3.elis.model.CourseModule;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.service.CourseMaterialService;
import com.g3.elis.service.CourseModuleService;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.EnrolledCourseService;

@Controller
@RequestMapping("/admin")
public class AdminEditCourseDetailController 
{
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseModuleService courseModuleService;
	
	@Autowired
	private CourseMaterialService courseMaterialService;
	
	@Autowired
	private EnrolledCourseService enrolledCourseService;
	
	@GetMapping("/admin-edit-course-detail")
	public String adminEditCourseDetail(@RequestParam(name = "courseId")int courseId,Model model)
	{
		Course course = courseService.getCourseById(courseId);
		int totalEnrolled = 0;
		int totalModules = 0;
		for(int i = 0;i < course.getCourseModule().size();i++) totalModules++;
		for(EnrolledCourse enrolledCourse : enrolledCourseService.getAllEnrolledCourse())
		{
			if(enrolledCourse.getCourses().getId() == course.getId())
			{
				totalEnrolled++;
			}
		}
		model.addAttribute("courseId",courseId);
		model.addAttribute("totalModules",totalModules);
		model.addAttribute("totalEnrolled",totalEnrolled);
		model.addAttribute("course",course);
		return "/admin/admin-edit-course-detail";
	}
	
	@GetMapping("/admin-edit-course-detail/add-module")
	public String adminAddModule(@RequestParam(name = "courseId")int courseId,Model model)
	{
		CourseModuleDto courseModuleDto = new CourseModuleDto();
		String action = "add";
		model.addAttribute("action",action);
		model.addAttribute("courseId",courseId);
		model.addAttribute("courseModuleDto",courseModuleDto);
		return "/admin/admin-edit-course-module";
	}
	
	@PostMapping("/admin-edit-course-detail/add-module")
	public String submitAdminAddModule(@ModelAttribute(name="courseModuleDto")CourseModuleDto courseModuleDto,
									   @RequestParam(name = "courseId")int courseId,Model model)
	{
		courseModuleService.createCourseModule(courseModuleDto,courseId);
		model.addAttribute("courseModuleDto",courseModuleDto);
		return "redirect:/admin/admin-edit-course-detail?courseId=" + courseId;
	}
	
	@GetMapping("/admin-edit-course-detail/edit-module")
	public String adminEditModule(@RequestParam(name = "courseId")int courseId,
								  @RequestParam(name = "courseModuleId")int courseModuleId,
								  Model model)
	{
		CourseModuleDto courseModuleDto = new CourseModuleDto();
		CourseModule courseModule = courseModuleService.getCourseModuleById(courseModuleId);
		courseModuleDto.setModuleTitle(courseModule.getModuleTitle());
		String action = "edit";
		model.addAttribute("action",action);
		model.addAttribute("courseId",courseId);
		model.addAttribute("courseModuleId",courseModuleId);
		model.addAttribute("courseModuleDto",courseModuleDto);
		return "/admin/admin-edit-course-module";
	}
	
	@PostMapping("/admin-edit-course-detail/edit-module")
	public String submitAdminEditModule(@ModelAttribute("courseModuleDto")CourseModuleDto courseModuleDto,
								  @RequestParam(name = "courseId")int courseId, 
								  @RequestParam(name = "courseModuleId")int courseModuleId,
								  Model model)
	{
		courseModuleService.editCourseModule(courseModuleDto, courseModuleId, courseId);
		return "redirect:/admin/admin-edit-course-detail?courseId=" + courseId;
	}
	@GetMapping("/admin-edit-course-detail/delete-module")
	public String adminDeleteModule(@RequestParam(name = "courseId")int courseId,
									@RequestParam(name = "courseModuleId")int courseModuleId,
									Model model)
	{
		courseModuleService.deleteCourseModuleById(courseModuleId);
		model.addAttribute("courseId",courseId);
		return "redirect:/admin/admin-edit-course-detail?courseId=" + courseId;
	}
	
	@GetMapping("/admin-edit-course-detail/add-material")
	public String adminAddMaterial(@RequestParam(name = "courseId")int courseId,
								   @RequestParam(name = "courseModuleId")int courseModuleId,
								   Model model)
	{
		CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
		String action = "add";
		model.addAttribute("courseMaterialDto",courseMaterialDto);
		model.addAttribute("action",action);
		model.addAttribute("courseId",courseId);
		model.addAttribute("courseModuleId",courseModuleId);
		return "/admin/admin-edit-course-material";
	}
	@PostMapping("/admin-edit-course-detail/add-material")
	public String submitAddMaterial(@ModelAttribute("courseMaterialDto")CourseMaterialDto courseMaterialDto,
									@RequestParam(name = "courseId")int courseId,
									@RequestParam(name = "courseModuleId")int courseModuleId) throws IOException
	{
		courseMaterialService.createCourseMaterial(courseMaterialDto, courseModuleId);
		return "redirect:/admin/admin-edit-course-detail?courseId=" + courseId;
	}
	
	@GetMapping("/admin-edit-course-detail/edit-material")
	public String adminEditMaterial(@RequestParam(name = "courseId")int courseId,
									@RequestParam(name = "courseModuleId")int courseModuleId,
									@RequestParam(name = "courseMaterialId")int courseMaterialId,				
									Model model)
	{
		CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
		CourseMaterial courseMaterial = courseMaterialService.getCourseMaterialById(courseMaterialId);
		String action = "edit";
		courseMaterialDto.setTitle(courseMaterial.getTitle());
		courseMaterialDto.setContent(courseMaterial.getContent());
		model.addAttribute("courseId",courseId);
		model.addAttribute("courseModuleId",courseModuleId);
		model.addAttribute("action",action);
		model.addAttribute("courseMaterialId",courseMaterialId);
		model.addAttribute("courseMaterialDto",courseMaterialDto);
		return "/admin/admin-edit-course-material";
	}
	@PostMapping("/admin-edit-course-detail/edit-material")
	public String submiEditeMaterial(@ModelAttribute("courseMaterialDto")CourseMaterialDto courseMaterialDto,
									  @RequestParam(name = "courseId")int courseId,
									  @RequestParam(name = "courseModuleId")int courseModuleId,
									  @RequestParam(name = "courseMaterialId")int courseMaterialId) throws IOException
	{
		courseMaterialService.editCourseMaterial(courseMaterialDto, courseMaterialId, courseModuleId);
		return "redirect:/admin/admin-edit-course-detail?courseId=" + courseId;
	}
	@GetMapping("/admin-edit-course-detail/delete-material")
	public String adminDeleteMaterial(@RequestParam(name = "courseId")int courseId,
									  @RequestParam(name = "courseMaterialId")int courseMaterialId)
	{
		courseMaterialService.deleteCourseMaterialById(courseMaterialId);
		return "redirect:/admin/admin-edit-course-detail?courseId=" + courseId;
	}
}
