package com.g3.elis.util;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.dto.report.CoursePerformance;
import com.g3.elis.dto.report.CourseProgress;
import com.g3.elis.dto.report.QuizPerformance;
import com.g3.elis.model.InputFile;



public interface InputFileService {
	String determineFileType(MultipartFile file);
	void WriteEmployeeDataFromExcel(MultipartFile excelFile) throws IOException;
	void generateCoursePerformanceExcelReportFile(List<CoursePerformance> reportData);
	void generateCourseProgressExcelReportFile(List<CourseProgress> reportData);
	void generateQuizPerformanceExcelReportFile(List<QuizPerformance> reportData);
	void saveFile(MultipartFile file,String filePath);
	void saveYouTubeVideoFile(String url);
	List<InputFile> getAllInputFiles();
	List<InputFile> getAllImageFiles();
	List<InputFile> getAllVideoFiles();
	List<InputFile> getAllYouTubeUrl();
	List<InputFile> getAllExcelFiles();
	List<SheetData> readExcel(InputFile file) throws IOException;
}	