package com.g3.elis.service;

import java.util.List;

import com.g3.elis.dto.report.CoursePerformance;
import com.g3.elis.dto.report.CourseProgress;
import com.g3.elis.dto.report.QuizPerformance;
import com.g3.elis.model.Report;

public interface ReportService {
    
	List<CoursePerformance> generateCoursePerformanceReport();
	List<CourseProgress> generateCourseProgressReport(int courseId);
	List<QuizPerformance> generateQuizPerformanceReport(int instructorId);
	List<Integer> generateEnrolledUserByMonthDataForApexChart();                 // For Admin
	List<Integer> generateEnrolledUserByMonthDataForApexChart(int instructorId); // For Instructor
	List<Integer> generateGradesResultsForTrafficChart();
	List<Integer> generateYearlyTotalCourseCompletedForActiveChartStudent(int courseId);
	List<Integer> generateYearlyTotalEnrolledForActiveChartStudent(int courseId);
}