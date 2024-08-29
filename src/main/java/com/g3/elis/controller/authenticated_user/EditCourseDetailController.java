package com.g3.elis.controller.authenticated_user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.form.AnswerDto;
import com.g3.elis.dto.form.CourseAssignmentDto;
import com.g3.elis.dto.form.CourseMaterialDto;
import com.g3.elis.dto.form.CourseModuleDto;
import com.g3.elis.dto.form.QuestionCreationSuperDto;
import com.g3.elis.dto.form.QuestionDto;
import com.g3.elis.model.Answer;
import com.g3.elis.model.Course;
import com.g3.elis.model.CourseAssignment;
import com.g3.elis.model.CourseMaterial;
import com.g3.elis.model.CourseModule;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.Question;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.AnswerService;
import com.g3.elis.service.CourseAssignmentService;
import com.g3.elis.service.CourseMaterialService;
import com.g3.elis.service.CourseModuleService;
import com.g3.elis.service.CourseService;
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.QuestionService;
import com.g3.elis.service.ReportService;

@Controller
@RequestMapping({"/admin","/instructor"})
@SessionAttributes("superDto")
public class EditCourseDetailController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseModuleService courseModuleService;

	@Autowired
	private CourseMaterialService courseMaterialService;

	@Autowired
	private EnrolledCourseService enrolledCourseService;

	@Autowired
	private CourseAssignmentService courseAssignmentService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private ReportService reportService;
	
	@ModelAttribute("superDto")
    public QuestionCreationSuperDto populatesuperDto() {
		QuestionCreationSuperDto superDto = new QuestionCreationSuperDto();
		superDto.setQuestionDto(new QuestionDto());
		superDto.setAnswerDtoList(new ArrayList<AnswerDto>());;
        return superDto;
    }

	@GetMapping("/edit-course-detail")
	public String adminEditCourseDetail(@RequestParam(name = "courseId") int courseId, Model model) {
		Course course = courseService.getCourseById(courseId);
		int totalEnrolled = 0;
		int totalModules = 0;
		for (int i = 0; i < course.getCourseModule().size(); i++)
			totalModules++;
		for (EnrolledCourse enrolledCourse : enrolledCourseService.getAllEnrolledCourse()) {
			if (enrolledCourse.getCourses().getId() == course.getId()) 
			{
				totalEnrolled++;	
			}
		}
		List<Integer> activeChartStudent = reportService.generateYearlyTotalCourseCompletedForActiveChartStudent(courseId);
		int totalCourseComplete = 0;
		for(int i = 0; i < activeChartStudent.size();i++)
		{
			totalCourseComplete+= activeChartStudent.get(i);
		}
		model.addAttribute("activeChartStudent",reportService.generateYearlyTotalCourseCompletedForActiveChartStudent(courseId));
		model.addAttribute("activeChartStudent2",reportService.generateYearlyTotalEnrolledForActiveChartStudent(courseId));
		model.addAttribute("courseId", courseId);
		model.addAttribute("totalModules", totalModules);
		model.addAttribute("totalEnrolled", totalEnrolled);
		model.addAttribute("totalCourseComplete",totalCourseComplete);
		model.addAttribute("course", course);
		model.addAttribute("map",determineMapping());
		
	return "/authenticated-user/edit-course-detail";
	}

	
	
	@GetMapping("/edit-course-detail/add-module")
	public String adminAddModule(@RequestParam(name = "courseId") int courseId, Model model) {
		CourseModuleDto courseModuleDto = new CourseModuleDto();
		Course course = courseService.getCourseById(courseId);
		String action = "add";
		model.addAttribute("course", course);
		model.addAttribute("action", action);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleDto", courseModuleDto);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-course-module";
	}

	@PostMapping("/edit-course-detail/add-module")
	public String submitAdminAddModule(@ModelAttribute(name = "courseModuleDto") CourseModuleDto courseModuleDto,
			@RequestParam(name = "courseId") int courseId, Model model) {
		courseModuleService.createCourseModule(courseModuleDto, courseId);
		model.addAttribute("courseModuleDto", courseModuleDto);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/edit-module")
	public String adminEditModule(@RequestParam(name = "courseId") int courseId,
			@RequestParam(name = "courseModuleId") int courseModuleId, Model model) {
		CourseModuleDto courseModuleDto = new CourseModuleDto();
		CourseModule courseModule = courseModuleService.getCourseModuleById(courseModuleId);
		Course course = courseService.getCourseById(courseId);
		courseModuleDto.setModuleTitle(courseModule.getModuleTitle());
		String action = "edit";
		model.addAttribute("course", course);
		model.addAttribute("action", action);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId", courseModuleId);
		model.addAttribute("courseModuleDto", courseModuleDto);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-course-module";
	}

	@PostMapping("/edit-course-detail/edit-module")
	public String submitAdminEditModule(@ModelAttribute("courseModuleDto") CourseModuleDto courseModuleDto,
			@RequestParam(name = "courseId") int courseId, @RequestParam(name = "courseModuleId") int courseModuleId,
			Model model) {
		courseModuleService.editCourseModule(courseModuleDto, courseModuleId, courseId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/delete-module")
	public String adminDeleteModule(@RequestParam(name = "courseId") int courseId,
			@RequestParam(name = "courseModuleId") int courseModuleId, Model model) {
		courseModuleService.deleteCourseModuleById(courseModuleId);
		model.addAttribute("courseId", courseId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/add-material")
	public String adminAddMaterial(@RequestParam(name = "courseId") int courseId,
			@RequestParam(name = "courseModuleId") int courseModuleId, Model model) {
		CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
		Course course = courseService.getCourseById(courseId);
		String action = "add";
		model.addAttribute("course", course);
		model.addAttribute("courseMaterialDto", courseMaterialDto);
		model.addAttribute("action", action);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId", courseModuleId);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/add-course-material";
	}

	@PostMapping("/edit-course-detail/add-material")
	public String submitAddMaterial(@ModelAttribute("courseMaterialDto") CourseMaterialDto courseMaterialDto,
									@RequestParam(name = "courseId") int courseId, 
									@RequestParam(name = "courseModuleId") int courseModuleId,
									@RequestParam(name = "content")String content,
									@RequestParam(name = "inputFile")MultipartFile inputFile,
									Model model) throws IOException 
	{
		courseMaterialDto.setContent(content);		
		courseMaterialService.createCourseMaterial(courseMaterialDto, courseModuleId,inputFile);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/edit-material")
	public String adminEditMaterial(@RequestParam(name = "courseId") int courseId,
			@RequestParam(name = "courseModuleId") int courseModuleId,
			@RequestParam(name = "courseMaterialId") int courseMaterialId, Model model) {
		CourseMaterialDto courseMaterialDto = new CourseMaterialDto();
		CourseMaterial courseMaterial = courseMaterialService.getCourseMaterialById(courseMaterialId);
		Course course = courseService.getCourseById(courseId);
		String action = "edit";
		courseMaterialDto.setTitle(courseMaterial.getTitle());
		courseMaterialDto.setContent(courseMaterial.getContent());
		model.addAttribute("course", course);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId", courseModuleId);
		model.addAttribute("action", action);
		model.addAttribute("courseMaterialId", courseMaterialId);
		model.addAttribute("courseMaterialDto", courseMaterialDto);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-course-material";
	}

	@PostMapping("/edit-course-detail/edit-material")
	public String submiEditeMaterial(@ModelAttribute("courseMaterialDto") CourseMaterialDto courseMaterialDto,
									 @RequestParam(name = "courseId") int courseId,
									 @RequestParam(name = "courseModuleId") int courseModuleId,
									 @RequestParam(name = "courseMaterialId") int courseMaterialId,
									 @RequestParam(name = "content")String content,
									 @RequestParam(name = "inputFile")MultipartFile inputFile,
									 Model model) throws IOException 
	{
		courseMaterialDto.setContent(content);
		courseMaterialService.editCourseMaterial(courseMaterialDto, courseMaterialId, courseModuleId,inputFile);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/delete-material")
	public String adminDeleteMaterial(@RequestParam(name = "courseId") int courseId,
									  @RequestParam(name = "courseMaterialId") int courseMaterialId,
									  Model model) 
	{
		courseMaterialService.deleteCourseMaterialById(courseMaterialId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/add-assignment")
	public String adminaddAssignment(@RequestParam(name = "courseId") int courseId,
									 @RequestParam(name = "courseModuleId") int courseModuleId, 
									 Model model) 
	{
		CourseAssignmentDto courseAssignmentDto = new CourseAssignmentDto();
		Course course = courseService.getCourseById(courseId);
		String action = "add";
		model.addAttribute("courseAssignmentDto", courseAssignmentDto);
		model.addAttribute("action", action);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId", courseModuleId);
		model.addAttribute("course", course);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-assignment";
	}

	@PostMapping("/edit-course-detail/add-assignment")
	public String submitAddAssignment(@ModelAttribute("courseAssignmentDto") CourseAssignmentDto courseAssignmentDto,
									  @RequestParam(name = "courseId") int courseId, 
									  @RequestParam(name = "courseModuleId") int courseModuleId,
									  Model model) 
	{
		courseAssignmentService.createAssignment(courseAssignmentDto, courseId, courseModuleId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/edit-assignment")
	public String adminEditAssignment(@RequestParam(name = "courseId") int courseId,
									  @RequestParam(name = "courseModuleId") int courseModuleId,
									  @RequestParam(name = "courseAssignmentId") int courseAssignmentId, Model model) {
		CourseAssignmentDto courseAssignmentDto = new CourseAssignmentDto();
		CourseAssignment courseAssignment = courseAssignmentService.getCourseAssignmentById(courseAssignmentId);
		courseAssignmentDto.setTitle(courseAssignment.getTitle());
		Course course = courseService.getCourseById(courseId);
		String action = "edit";
		model.addAttribute("courseAssignment", courseAssignment);
		model.addAttribute("courseAssignmentDto", courseAssignmentDto);
		model.addAttribute("action", action);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId", courseModuleId);
		model.addAttribute("courseAssignmentId",courseAssignmentId);
		model.addAttribute("course", course);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-assignment";
	}

	@PostMapping("/edit-course-detail/edit-assignment")
	public String submitEditAssignment(@ModelAttribute("courseAssignmentDto") CourseAssignmentDto courseAssignmentDto,
									   @RequestParam(name = "courseId") int courseId, 
									   @RequestParam(name = "courseModuleId") int courseModuleId,
									   @RequestParam(name = "courseAssignmentId") int courseAssignmentId,
									   Model model) {
		courseAssignmentService.editAssignment(courseAssignmentDto, courseModuleId, courseAssignmentId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/delete-assignment")
	public String deleteAssignment(@RequestParam(name = "courseId") int courseId,
								   @RequestParam(name = "courseModuleId") int courseModuleId,
								   @RequestParam(name = "courseAssignmentId") int courseAssignmentId,
								   Model model) 
	{
		courseAssignmentService.deleteAssignment(courseModuleId, courseAssignmentId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail?courseId=" + courseId;
	}

	@GetMapping("/edit-course-detail/edit-assignment/add-question")
	public String adminAddQuestion(@ModelAttribute("superDto")QuestionCreationSuperDto superDto,
								   @RequestParam(name = "courseId") int courseId,
								   @RequestParam(name = "courseModuleId") int courseModuleId,
								   @RequestParam(name = "courseAssignmentId") int courseAssignmentId, Model model) {
		Course course = courseService.getCourseById(courseId);
		superDto = new QuestionCreationSuperDto();
		superDto.setAnswerDtoList(new ArrayList<AnswerDto>());
		superDto.setQuestionDto(new QuestionDto());
		String action = "add";
		model.addAttribute("action", action);
		model.addAttribute("superDto", superDto);
		model.addAttribute("course", course);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId",courseModuleId);
		model.addAttribute("courseAssignmentId", courseAssignmentId);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-question";
	}
	
	@PostMapping("/edit-course-detail/edit-assignment/add-question")
	public String submitAddQuestion(@ModelAttribute("superDto") QuestionDto superDto,
									@RequestParam(name = "courseModuleId") int courseModuleId, 
									@RequestParam(name = "courseId") int courseId,
									@RequestParam(name = "courseAssignmentId") int courseAssignmentId,
									Model model) 
	{
		questionService.createQuestion(superDto, courseAssignmentId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail/edit-assignment?courseId=" + courseId + "&courseModuleId="
				+ courseModuleId + "&courseAssignmentId=" + courseAssignmentId;
	}

	@GetMapping("/edit-course-detail/edit-assignment/edit-question")
	public String adminEditQuestion(@RequestParam(name = "courseId") int courseId,
									@RequestParam(name = "courseModuleId")int courseModuleId,
									@RequestParam(name = "courseAssignmentId") int courseAssignmentId,
									@RequestParam(name = "questionId") int questionId, Model model) 
	{
		Course course = courseService.getCourseById(courseId);
		QuestionDto questionDto = new QuestionDto();
		Question question = questionService.getQuestionById(questionId);
		questionDto.setQuestion(question.getQuestion());
		questionDto.setCorrectStatus(question.isCorrectStatus());
		String action = "edit";
		model.addAttribute("action", action);
		model.addAttribute("questionDto", questionDto);
		model.addAttribute("course", course);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId",courseModuleId);
		model.addAttribute("questionId",questionId);
		model.addAttribute("courseAssignmentId", courseAssignmentId);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-question";
	}

	@PostMapping("/edit-course-detail/edit-assignment/edit-question")
	public String submitEditQuestion(@ModelAttribute("questionDto")QuestionDto questionDto,
									 @RequestParam(name = "courseId") int courseId,
									 @RequestParam(name = "courseModuleId")int courseModuleId,
									 @RequestParam(name = "courseAssignmentId") int courseAssignmentId,
									 @RequestParam(name = "questionId") int questionId,
									 Model model) 
	{
		CourseAssignmentDto courseAssignmentDto = new CourseAssignmentDto();
		CourseAssignment courseAssignment = courseAssignmentService.getCourseAssignmentById(courseAssignmentId);
		courseAssignmentDto.setTitle(courseAssignment.getTitle());
		Course course = courseService.getCourseById(courseId);
		String action = "edit";
		questionService.editQuestion(questionDto, courseAssignmentId, questionId);
		model.addAttribute("course",course);
		model.addAttribute("action",action);
		model.addAttribute("courseAssignmentDto",courseAssignmentDto);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail/edit-assignment?courseId=" + courseId + "&courseModuleId="
		+ courseModuleId + "&courseAssignmentId=" + courseAssignmentId;
	}
	
	@GetMapping("/edit-course-detail/edit-assignment/delete-question")
	public String deleteQuestion(@RequestParam(name = "courseId") int courseId,
								 @RequestParam(name = "courseModuleId")int courseModuleId,
								 @RequestParam(name = "courseAssignmentId") int courseAssignmentId,
								 @RequestParam(name = "questionId") int questionId) 
	{
		questionService.deleteQuestion(questionId);
		return "redirect:/"+ determineMapping() +"/edit-course-detail/edit-assignment?courseId=" + courseId + "&courseModuleId="
		+ courseModuleId + "&courseAssignmentId=" + courseAssignmentId;
	}
	
	@GetMapping("/edit-course-detail/edit-assignment/add-question/add-answer")
	public String adminAddAnswer(@ModelAttribute("superDto")QuestionCreationSuperDto superDto,
								 @RequestParam(name = "answerCount")int answerCount,
								 @RequestParam(name = "courseId") int courseId,
			   					 @RequestParam(name = "courseModuleId") int courseModuleId,
			   					 @RequestParam(name = "courseAssignmentId") int courseAssignmentId, 
			   					 @RequestParam(name = "action")String action,
			   					 Model model)
	{
		AnswerDto answerDto = new AnswerDto();
		superDto.getAnswerDtoList().clear();
		for(int i = 0; i< answerCount;i++) superDto.getAnswerDtoList().add(answerDto);
		model.addAttribute("action", action);
		model.addAttribute("superDto", superDto);
		model.addAttribute("course", courseService.getCourseById(courseId));
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId",courseModuleId);
		model.addAttribute("courseAssignmentId", courseAssignmentId);
		model.addAttribute("map",determineMapping());
		model.addAttribute("answerCount", answerCount);
		
		return "/authenticated-user/edit-question";
	}
	
	@PostMapping("/edit-course-detail/edit-assignment/add-question/add-answer")
	public String adminSubmitAnswer(@ModelAttribute("superDto")QuestionCreationSuperDto superDto,
									@RequestParam(name = "courseId") int courseId,
									@RequestParam(name = "courseModuleId") int courseModuleId,
									@RequestParam(name = "courseAssignmentId") int courseAssignmentId,
									Model model)
	{
		questionService.createQuestionAndAnswer(superDto, courseAssignmentId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail/edit-assignment?courseId=" + courseId + "&courseModuleId="
		+ courseModuleId + "&courseAssignmentId=" + courseAssignmentId;
	}
	
	@GetMapping("/edit-course-detail/edit-assignment/add-question/add-single-answer")
	public String adminAddSingleAnswer(@RequestParam(name = "courseId") int courseId,
									   @RequestParam(name = "courseModuleId") int courseModuleId,
									   @RequestParam(name = "courseAssignmentId") int courseAssignmentId,
									   @RequestParam(name = "questionId")int questionId,
									   Model model)
	{
		AnswerDto answerDto = new AnswerDto();
		String action = "add";
		model.addAttribute("action", action);
		model.addAttribute("course", courseService.getCourseById(courseId));
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId",courseModuleId);
		model.addAttribute("courseAssignmentId", courseAssignmentId);
		model.addAttribute("questionId",questionId);
		model.addAttribute("answerDto",answerDto);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-answer";
	}
	
	@PostMapping("/edit-course-detail/edit-assignment/add-question/add-single-answer")
	public String submitAddSingleAnswer(@ModelAttribute("answerDto")AnswerDto answerDto,
										@RequestParam(name = "courseId") int courseId,
			   							@RequestParam(name = "courseModuleId") int courseModuleId,
			   							@RequestParam(name = "courseAssignmentId") int courseAssignmentId,
			   							@RequestParam(name = "questionId")int questionId,Model model)
	{
		answerService.addAnswer(answerDto, questionId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail/edit-assignment?courseId=" + courseId + "&courseModuleId="
		+ courseModuleId + "&courseAssignmentId=" + courseAssignmentId;
	}
	
	@GetMapping("/edit-course-detail/edit-assignment/add-question/edit-answer")
	public String adminEditAnswer(@RequestParam(name = "courseId") int courseId,
								  @RequestParam(name = "courseModuleId") int courseModuleId,
								  @RequestParam(name = "courseAssignmentId") int courseAssignmentId,
								  @RequestParam(name = "questionId")int questionId,
								  @RequestParam(name = "answerId")int answerId,
								  Model model)
	{
		Answer answer = answerService.getAnswerById(answerId);
		AnswerDto answerDto = new AnswerDto();
		answerDto.setAnswerTitle(answer.getAnswerTitle());
		answerDto.setCorrectStatus(answer.isCorrectStatus());
		String action = "edit";
		model.addAttribute("action", action);
		model.addAttribute("course", courseService.getCourseById(courseId));
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseModuleId",courseModuleId);
		model.addAttribute("courseAssignmentId", courseAssignmentId);
		model.addAttribute("questionId",questionId);
		model.addAttribute("answerId",answerId); 	
		model.addAttribute("answerDto",answerDto);
		model.addAttribute("map",determineMapping());
		return "/authenticated-user/edit-answer";
	}
	
	@PostMapping("/edit-course-detail/edit-assignment/add-question/edit-answer")
	public String submitEditAnswer(@ModelAttribute("answerDto")AnswerDto answerDto,
								   @RequestParam(name = "courseId") int courseId,
							       @RequestParam(name = "courseModuleId") int courseModuleId,
							       @RequestParam(name = "courseAssignmentId") int courseAssignmentId,
							       @RequestParam(name = "questionId")int questionId,
							       @RequestParam(name = "answerId")int answerId,
							       Model model)
	{
		answerService.editAnswer(answerDto, answerId, questionId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail/edit-assignment?courseId=" + courseId + "&courseModuleId="
		+ courseModuleId + "&courseAssignmentId=" + courseAssignmentId;
	}
	
	@GetMapping("/edit-course-detail/edit-assignment/add-question/delete-answer")
	public String deleteAnswer(@RequestParam(name = "courseId")int courseId,
							   @RequestParam(name = "courseModuleId")int courseModuleId,
			 				   @RequestParam(name = "courseAssignmentId") int courseAssignmentId,
			 				   @RequestParam(name = "questionId") int questionId,
			 				   @RequestParam(name = "answerId")int answerId,
			 				   Model model)
	{
		answerService.deleteAnswer(answerId);
		model.addAttribute("map",determineMapping());
		return "redirect:/"+ determineMapping() +"/edit-course-detail/edit-assignment?courseId=" + courseId + "&courseModuleId="
		+ courseModuleId + "&courseAssignmentId=" + courseAssignmentId;
	}
	
	private String determineMapping() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetail userDetail = (LoginUserDetail) authentication.getPrincipal();
        if(userDetail.isAdmin()) {
            return "admin";
        }
        return "instructor";
    }
	
	
}