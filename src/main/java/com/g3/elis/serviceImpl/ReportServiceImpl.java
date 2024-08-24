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
import com.g3.elis.service.EnrolledCourseService;
import com.g3.elis.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EnrolledCourseService enrolledCourseService;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<CoursePerformance> generateCoursePerformanceReport() {
		List<CoursePerformance> report = new ArrayList<CoursePerformance>();
		List<Course> courses = courseRepository.findAll();
		courses.sort(Comparator.comparingInt(course -> ((Course) course).getEnrolledCourses().size()).reversed());

		for (Course course : courses) {
			CoursePerformance coursePerformanceDto = new CoursePerformance();
			coursePerformanceDto.setInstructor(course.getUsers().getName());
			coursePerformanceDto.setEnrollment(course.getEnrolledCourses().size());
			coursePerformanceDto.setCourseId(course.getId());
			coursePerformanceDto.setCreatedAt(course.getCreatedAt());
			coursePerformanceDto.setCourseTitle(course.getCourseTitle());
			coursePerformanceDto.setCourseImage(course.getCourseImageFileName());

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
				if (enrolledCourse.getEnrolledAt().toString().substring(0, 7)
						.equalsIgnoreCase(Timestamp.valueOf(LocalDateTime.now()).toString().substring(0, 7)))
					monthlyEnrolledUser++;
				if (enrolledCourse.getEnrolledAt().toString().substring(0, 4)
						.equalsIgnoreCase(Timestamp.valueOf(LocalDateTime.now()).toString().substring(0, 4)))
					yearlyEnrolledUser++;

			}
			coursePerformanceDto.setCompletionRate(((totalCourseComplete / course.getEnrolledCourses().size()) * 100));
			coursePerformanceDto.setAverageScore(totalScore / gradeCount);

			coursePerformanceDto.setMonthlyEnrolledUser(monthlyEnrolledUser);
			coursePerformanceDto.setYearlyEnrolledUser(yearlyEnrolledUser);

			report.add(coursePerformanceDto);
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
					if(enrolledAssignment.getGrades() != null)
					{
						int totalScore = 0;
						int highestScoreCount = 0;
						int lowestScoreCount = 0;
						for(Grade grade : enrolledAssignment.getGrades())
						{
							totalScore+=grade.getScore();
							if(grade.getScore() >=80) highestScoreCount++;
							if(grade.getScore() <=79) lowestScoreCount++;
							
							quizPerformance.setAssignmentTitle(enrolledAssignment.getCourseAssignment().getTitle());
						}
						if(enrolledAssignment.isCompleteStatus() == true) assignmentCompleteCount++;
						
						quizPerformance.setAverageScore(( (double) totalScore ) / ( (double) enrolledAssignment.getGrades().size()));
						quizPerformance.setHighestScore(( (double) highestScoreCount) / ((double) enrolledAssignment.getGrades().size()) * 100);
						quizPerformance.setHighestScore(( (double) lowestScoreCount) / ((double) enrolledAssignment.getGrades().size()) * 100);
						quizPerformance.setPassRate(((double) assignmentCompleteCount)/ ((double)enrolledModle.getEnrolledAssignment().size()) * 100);
						quizPerformance.setStudentName(enrolledCourse.getUsers().getName());
						quizPerformance.setStudentName(enrolledCourse.getUsers().getProfile() == null ? null : enrolledCourse.getUsers().getProfile().getProfileImg());
						report.add(quizPerformance);
					}					
				}
			}
		}
		report.sort(Comparator.comparingDouble(quizPerformance -> quizPerformance.getAverageScore()));
		
		return report;
	}

}
