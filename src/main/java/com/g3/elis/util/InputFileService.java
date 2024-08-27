package com.g3.elis.util;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.report.CoursePerformance;
import com.g3.elis.dto.report.CourseProgress;
import com.g3.elis.dto.report.QuizPerformance;
import com.g3.elis.model.EnrolledMaterial;
import com.g3.elis.model.InputFile;



public interface InputFileService {
	void WriteEmployeeDataFromExcel(MultipartFile excelFile) throws IOException;
	void generateCoursePerformanceExcelReportFile(List<CoursePerformance> reportData);
	void generateCourseProgressExcelReportFile(List<CourseProgress> reportData);
	void generateQuizPerformanceExcelReportFile(List<QuizPerformance> reportData);
	void saveYouTubeVideoFile(String url);
	String determineFileType(EnrolledMaterial enrolledMaterial);
//	String getImageFile(EnrolledMaterial enrolledMaterial);
//	String getVideoFile(EnrolledMaterial enrolledMaterial);
//	String getYouTubeUrl(EnrolledMaterial enrolledMaterial);
//	String getExcelFile(EnrolledMaterial enrolledMaterial);
	List<SheetData> readExcel(String file) throws IOException;
}	
