package com.g3.elis.service;

import java.util.List;

import com.g3.elis.model.Report;

public interface ReportService {
    
    List<Report> generateCourseProgressReport();
    
    List<Report> generateAchievementsReport();
    
    List<Report> generateStudentAnswersReport();
    
    List<Report> generateUserActivityLog();
}
