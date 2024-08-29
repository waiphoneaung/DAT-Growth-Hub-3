package com.g3.elis.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.dto.report.CoursePerformance;
import com.g3.elis.dto.report.CourseProgress;
import com.g3.elis.dto.report.QuizPerformance;
import com.g3.elis.model.Course;
import com.g3.elis.model.EnrolledAssignment;
import com.g3.elis.model.EnrolledCourse;
import com.g3.elis.model.EnrolledModule;
import com.g3.elis.model.Grade;
import com.g3.elis.model.Report;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.repository.EnrolledCourseRepository;
import com.g3.elis.repository.GradeRepository;
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EnrolledCourseService enrolledCourseService;

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public List<CoursePerformance> generateCoursePerformanceReport() {
		List<CoursePerformance> report = new ArrayList<CoursePerformance>();
		List<Course> courses = courseRepository.findAll();
		courses.sort(Comparator.comparingInt(course -> ((Course) course).getEnrolledCourses().size()).reversed());

		for (Course course : courses) 
		{
			CoursePerformance coursePerformance = new CoursePerformance();
			coursePerformance.setInstructor(course.getUsers().getName());
			coursePerformance.setEnrollment(course.getEnrolledCourses().size());
			coursePerformance.setCourseId(course.getId());
			coursePerformance.setCreatedAt(course.getCreatedAt());
			coursePerformance.setCourseTitle(course.getCourseTitle());
			coursePerformance.setCourseImage(course.getCourseImageFileName());

			double totalCourseComplete = 0;
			double totalScore = 0;
			double gradeCount = 0;
			int monthlyEnrolledUser = 0;
			int yearlyEnrolledUser = 0;
			for (EnrolledCourse enrolledCourse : course.getEnrolledCourses()) {
				if (enrolledCourse.isCompleteStatus())
					totalCourseComplete++;
				for (EnrolledModule enrolledModule : enrolledCourse.getEnrolledModules()) {
					for (EnrolledAssignment enrolledAssignment : enrolledModule.getEnrolledAssignment()) {
						for (Grade grade : enrolledAssignment.getGrades()) {
							totalScore += grade.getScore();
							gradeCount++;
						}
					}
				}
				if (enrolledCourse.getEnrolledAt().toString().substring(0, 7).equalsIgnoreCase(Timestamp.valueOf(LocalDateTime.now()).toString().substring(0, 7))) monthlyEnrolledUser++;
				if (enrolledCourse.getEnrolledAt().toString().substring(0, 4).equalsIgnoreCase(Timestamp.valueOf(LocalDateTime.now()).toString().substring(0, 4))) yearlyEnrolledUser++;

			}
			coursePerformance.setCompletionRate(((totalCourseComplete / course.getEnrolledCourses().size()) * 100));
			coursePerformance.setAverageScore(totalScore / gradeCount);

			coursePerformance.setMonthlyEnrolledUser(monthlyEnrolledUser);
			coursePerformance.setYearlyEnrolledUser(yearlyEnrolledUser);

			report.add(coursePerformance);
		}

		return report;
	}

	@Override
	public List<CourseProgress> generateCourseProgressReport(int courseId) {
		List<CourseProgress> report = new ArrayList<CourseProgress>();
		List<EnrolledCourse> enrolledCourses = enrolledCourseService.getAllEnrolledCourseByCourseId(courseId);

		for (EnrolledCourse enrolledCourse : enrolledCourses) {
			CourseProgress courseProgress = new CourseProgress();

			courseProgress.setCourseId(enrolledCourse.getCourses().getId());
			courseProgress.setCourseImage(enrolledCourse.getCourses().getCourseImageFileName());
			courseProgress.setCourseTitle(enrolledCourse.getCourses().getCourseTitle());
			courseProgress.setEnrolledDate(enrolledCourse.getEnrolledAt());
			courseProgress.setEnrolledUserId(enrolledCourse.getUsers().getId());
			courseProgress.setEnrolledUserName(enrolledCourse.getUsers().getName());
			courseProgress.setStaffId(enrolledCourse.getUsers().getStaffId());
			if (enrolledCourse.getUsers().getProfile() != null)
				courseProgress.setEnrolledUserProfileImage(enrolledCourse.getUsers().getProfile().getProfileImg());

			double completeEnrolledModules = 0;
			for (EnrolledModule enrolledModule : enrolledCourse.getEnrolledModules()) {
				if (enrolledModule.isCompleteStatus() == true)
					completeEnrolledModules++;
			}
			courseProgress.setProgress(
					(completeEnrolledModules / (double) (enrolledCourse.getEnrolledModules().size())) * 100);
			double totalScore = 0;
			for (Grade grade : enrolledCourse.getUsers().getGrades()) {
				totalScore += grade.getScore();
			}

			courseProgress.setAverageScore(totalScore / enrolledCourse.getUsers().getGrades().size());

			courseProgress.setGrade(totalScore / enrolledCourse.getUsers().getGrades().size() >= 100 ? "S"
								  : totalScore / enrolledCourse.getUsers().getGrades().size() >= 80 ? "A"
							      : totalScore / enrolledCourse.getUsers().getGrades().size() >= 60 ? "B"
								  : totalScore / enrolledCourse.getUsers().getGrades().size() >= 40 ? "C" : "D");
			report.add(courseProgress);
		}

		return report;
	}

	@Override
	public List<QuizPerformance> generateQuizPerformanceReport(int instructorId) {
		List<QuizPerformance> report = new ArrayList<QuizPerformance>();
		List<EnrolledCourse> enrolledCourses = enrolledCourseService.getAllEnrolledCourseByInstructorId(instructorId);
		for(EnrolledCourse enrolledCourse : enrolledCourses)
		{
			for(EnrolledModule enrolledModle : enrolledCourse.getEnrolledModules())
			{
				int assignmentCompleteCount = 0;
				for(EnrolledAssignment enrolledAssignment : enrolledModle.getEnrolledAssignment())
				{
					QuizPerformance quizPerformance = new QuizPerformance();
					int totalScore = 0;
					int highestScoreCount = 0;
					int lowestScoreCount = 0;
					int timesTaken = 0;
					for(Grade grade : enrolledAssignment.getGrades())
					{
						totalScore+=grade.getScore();
						if(grade.getScore() >=80) highestScoreCount++;
						if(grade.getScore() <=79) lowestScoreCount++;
						if(grade.getUser().getId() == enrolledCourse.getUsers().getId()) timesTaken++;
						
					}
					if(enrolledAssignment.isCompleteStatus() == true) assignmentCompleteCount++;
					
					quizPerformance.setTimesTakenQuiz(timesTaken);
					quizPerformance.setAssignmentTitle(enrolledAssignment.getCourseAssignment().getTitle());
					quizPerformance.setAverageScore(( (double) totalScore ) / ( (double) enrolledAssignment.getGrades().size()));
					quizPerformance.setHighestScore(( (double) highestScoreCount) / ((double) enrolledAssignment.getGrades().size()) * 100);
					quizPerformance.setHighestScore(( (double) lowestScoreCount) / ((double) enrolledAssignment.getGrades().size()) * 100);
					quizPerformance.setPassRate(((double) assignmentCompleteCount)/ ((double)enrolledModle.getEnrolledAssignment().size()) * 100);
					quizPerformance.setStudentName(enrolledCourse.getUsers().getName());
					quizPerformance.setStudent(enrolledCourse.getUsers());
					quizPerformance.setCourseTitle(enrolledCourse.getCourses().getCourseTitle());
					quizPerformance.setStudentProfileImage(enrolledCourse.getUsers().getProfile() == null ? null : enrolledCourse.getUsers().getProfile().getProfileImg());
					
					quizPerformance.setGrade(( (double) totalScore ) / ( (double) enrolledAssignment.getGrades().size()) >= 100 ? "S"
							  		  	   : ( (double) totalScore ) / ( (double) enrolledAssignment.getGrades().size()) >= 80 ? "A"
								           : ( (double) totalScore ) / ( (double) enrolledAssignment.getGrades().size()) >= 60 ? "B"
									       : ( (double) totalScore ) / ( (double) enrolledAssignment.getGrades().size()) >= 40 ? "C" : "D");
					report.add(quizPerformance);			
				}
			}
		}
		report.sort(Comparator.comparingDouble(quizPerformance -> quizPerformance.getAverageScore()));
		
		return report;
	}

	@Override
	public List<Integer> generateEnrolledUserByMonthDataForApexChart() {
		List<EnrolledCourse> enrolledCourses = enrolledCourseService.getAllEnrolledCourse();
		List<Integer> apexChartData = new ArrayList<>();
		for(int i = 1; i <=12;i++)
		{
			int singleData = 0;
			for(EnrolledCourse enrolledCourse : enrolledCourses)
			{
				if(enrolledCourse.getEnrolledAt().toString().substring(5,7).contains(String.valueOf(i)) && 
				   enrolledCourse.getEnrolledAt().toString().substring(0,4).equals(Timestamp.valueOf(LocalDateTime.now()).toString().substring(0,4)))
				{
					singleData++;
				}
			}
			apexChartData.add(singleData);
		}
		
		return apexChartData;
	}
	
	@Override
	public List<Integer> generateEnrolledUserByMonthDataForApexChart(int instructorId) {
		List<EnrolledCourse> enrolledCourses = enrolledCourseService.getAllEnrolledCourseByInstructorId(instructorId);
		List<Integer> apexChartData = new ArrayList<>();
		for(int i = 1; i <=12;i++)
		{
			int singleData = 0;
			for(EnrolledCourse enrolledCourse : enrolledCourses)
			{
				if(enrolledCourse.getEnrolledAt().toString().substring(5,7).contains(String.valueOf(i)) && 
				   enrolledCourse.getEnrolledAt().toString().substring(0,4).equals(Timestamp.valueOf(LocalDateTime.now()).toString().substring(0,4)))
				{
					singleData++;
				}
			}
			apexChartData.add(singleData);
		}
		
		return apexChartData;
	}

	@Override
	public List<Integer> generateGradesResultsForTrafficChart() {
		List<Grade> grades = gradeRepository.findAll();
		List<Integer> dataForTrafficChart = new ArrayList<Integer>();
		int gradeS = 0,gradeA = 0,gradeB = 0,gradeC = 0,gradeD = 0;
		
		for(Grade grade : grades)
		{
			if(grade.getGrade().equals("S")) gradeS++;
			if(grade.getGrade().equals("A")) gradeA++;
			if(grade.getGrade().equals("B")) gradeB++;
			if(grade.getGrade().equals("C")) gradeC++;
			if(grade.getGrade().equals("D")) gradeD++;
		}
		dataForTrafficChart.add(gradeS);
		dataForTrafficChart.add(gradeA);
		dataForTrafficChart.add(gradeB);
		dataForTrafficChart.add(gradeC);
		dataForTrafficChart.add(gradeD);
		return dataForTrafficChart;
	}

	@Override
	public List<Integer> generateYearlyTotalCourseCompletedForActiveChartStudent(int courseId) 
	{
		List<EnrolledCourse> enrolledCourses = enrolledCourseService.getAllEnrolledCourseByCourseId(courseId);
		List<Integer> activeChartStudent = new ArrayList<>();
		for(int i = 1; i <=12;i++)
		{
			int singleData = 0;
			for(EnrolledCourse enrolledCourse : enrolledCourses)
			{
				if(enrolledCourse.getEnrolledAt().toString().substring(5,7).contains(String.valueOf(i)) && 
				   enrolledCourse.getEnrolledAt().toString().substring(0,4).equals(Timestamp.valueOf(LocalDateTime.now()).toString().substring(0,4)) && 
				   enrolledCourse.isCompleteStatus() == true)
				{
					singleData++;
				}
			}
			activeChartStudent.add(singleData);
		}
		return activeChartStudent;
	}

	@Override
	public List<Integer> generateYearlyTotalEnrolledForActiveChartStudent(int courseId) {
		List<EnrolledCourse> enrolledCourses = enrolledCourseService.getAllEnrolledCourseByCourseId(courseId);
		List<Integer> activeChartStudent = new ArrayList<>();
		for(int i = 1; i <=12;i++)
		{
			int singleData = 0;
			for(EnrolledCourse enrolledCourse : enrolledCourses)
			{
				if(enrolledCourse.getEnrolledAt().toString().substring(5,7).contains(String.valueOf(i)) && 
				   enrolledCourse.getEnrolledAt().toString().substring(0,4).equals(Timestamp.valueOf(LocalDateTime.now()).toString().substring(0,4)))
				{
					singleData++;
				}
			}
			activeChartStudent.add(singleData);
		}
		return activeChartStudent;
	}
}
