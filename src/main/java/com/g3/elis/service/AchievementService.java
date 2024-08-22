package com.g3.elis.service;

import java.util.List;

import com.g3.elis.model.Achievement;

public interface AchievementService 
{
	void createCertificate(int userId,int courseId);
	List<Achievement> getAllAchievement();
	Achievement getAchievementById(int achievementId);
}
