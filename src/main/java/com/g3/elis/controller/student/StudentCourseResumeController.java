 package com.g3.elis.controller.student;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.EnrolledMaterial;
import com.g3.elis.model.EnrolledModule;
import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.EnrolledMaterialService;
import com.g3.elis.service.EnrolledModuleService;
import com.g3.elis.util.InputFileService;
import com.g3.elis.util.SheetData;
import com.g3.elis.service.EnrolledCourseService;

@Controller
@RequestMapping("/student")
public class StudentCourseResumeController {
	@Autowired
	private EnrolledCourseService enrolledCourseService;

	@Autowired
	private EnrolledModuleService enrolledModuleService;

	@Autowired
	private EnrolledMaterialService enrollMaterialService;
	
	@Autowired
	private InputFileService inputFileService;

	@GetMapping("/student-course-resume")
	public String studentCourseResume(Authentication authentication, Model model) {
		LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
		User user = userDetail.getUser();
		List<EnrolledCourse> enrolledCourseList = enrolledCourseService.getAllEnrolledCourseByUserId(user.getId());
		for (EnrolledCourse enrolledCourse : enrolledCourseList) {
			for (EnrolledModule enrolledModule : enrolledCourse.getEnrolledModules()) {
				enrolledModuleService.setStatusToTrue(enrolledModule.getId());
			}
			enrolledCourseService.setStatusToTrue(enrolledCourse.getId());
		}
		enrolledCourseList.sort(Comparator.comparingDouble(enrolledCourse -> enrolledCourse.calculateProgress()));
		model.addAttribute("enrolledCourseList", enrolledCourseList);
		model.addAttribute("content", "student/student-course-resume");
		return "/student/student-layout";
	}

	@GetMapping("/student-course-resume/view-material")
	public String studentViewMaterial(@RequestParam(name = "enrollMaterialId") int enrollMaterialId, Model model) throws IOException 
	{
		EnrolledMaterial enrolledMaterial = enrollMaterialService.getEnrolledMaterialByEnrolledMaterialId(enrollMaterialId);
		model.addAttribute("enrolledMaterial",enrolledMaterial);
		model.addAttribute("fileType",inputFileService.determineFileType(enrolledMaterial));
		if(inputFileService.determineFileType(enrolledMaterial).equals("excel"))
		{
			List<SheetData> allSheetsData = inputFileService.readExcel(enrolledMaterial.getCourseMaterial().getInputFileName());
			model.addAttribute("sheetsData",allSheetsData);
		}
		else
		{
			model.addAttribute(inputFileService.determineFileType(enrolledMaterial),enrolledMaterial.getCourseMaterial().getInputFileName());
		}
		return "/student/student-course-material";
	}

	@GetMapping("/student-course-resume/complete-material")
	public String studentCompleteMaterial(@RequestParam(name = "enrollMaterialId") int enrollMaterialId, Model model) {
		enrollMaterialService.setStatusToTrue(enrollMaterialId);
		model.addAttribute("content", "student/student-course-resume");
		return "redirect:/student/student-course-resume";
	}

	// Continue course
	@GetMapping("/student-course-resume/{courseId}")
	public String continueCourse(@PathVariable("courseId") int courseId, Authentication authentication, Model model) {
		// Your logic for continuing the course, if any specific is needed
		return "redirect:/student/student-course-resume";
	}

	// Restart course
	@GetMapping("/student-course-restart/{courseId}")
	public String restartCourse(@PathVariable("courseId") int courseId, Authentication authentication, Model model) {
		// Fetch the course
		EnrolledCourse enrolledCourse = enrolledCourseService.getEnrolledCourseByEnrolledCourseId(courseId);

		// Reset statuses of enrolled modules
		for (EnrolledModule enrolledModule : enrolledCourse.getEnrolledModules()) {
			enrolledModule.setCompleteStatus(false);
			enrolledModuleService.save(enrolledModule);
		}

		// Reset the status of the enrolled course
		enrolledCourse.setCompleteStatus(false);
		enrolledCourse.setProgress(0);
		enrolledCourseService.save(enrolledCourse);

		// Reset the status of related enrolled materials and assignments if needed
		// This assumes you have a way to fetch these by enrolledCourse
		enrollMaterialService.resetMaterialsStatusByCourse(enrolledCourse.getId());

		model.addAttribute("content", "student/student-course-list");

		return "redirect:/student/student-layout";
	}
}
