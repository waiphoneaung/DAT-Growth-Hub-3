package com.g3.elis.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.EnrolledCourseService;



@ControllerAdvice
public class GlobalControllerAdvice {
	
	@Autowired
	CourseService courseService;

	@Autowired
	EnrolledCourseService enrollerCourseService;


	
    @ModelAttribute
    public void addUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            System.out.println("Principal class: " + principal.getClass());
            if (principal instanceof LoginUserDetail) {
                LoginUserDetail userDetails = (LoginUserDetail) principal;
                User user = userDetails.getUser();
                int userId = user.getId();
                
                //for course count in instructor role
                List<User> userone= enrollerCourseService.getEnrolledStudentsByCourseCreatedByInstructorId(userId);
        		int enrolledStudentNumber = userone.size();

        		int totalCount = courseService.getTotalCourseCourseByUser(userId);
        		int activatedCount = courseService.getTotalActivatedCourseCountByUser(userId);
        		model.addAttribute("enrolledStudentNumber", enrolledStudentNumber);
        		model.addAttribute("totalCount", totalCount);
        		model.addAttribute("activatedCount", activatedCount);

                model.addAttribute("user", user);
                model.addAttribute("user_id", userId);
            }
        }
    }
}