package com.g3.elis.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.Achievement;
import com.g3.elis.model.Course;
import com.g3.elis.model.User;
import com.g3.elis.repository.AchievementRepository;
import com.g3.elis.repository.CourseRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.service.AchievementService;

@Service
public class AchievementServiceImpl implements AchievementService 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private AchievementRepository achievementRepository;

	@Override
	public void createCertificate(int userId, int courseId) {
		User user = userRepository.findById(userId).orElse(null);
		Course course = courseRepository.findById(courseId).orElse(null);
		List<Achievement> achievementList = achievementRepository.findAll();
		for(Achievement achievemnt : achievementList)
		{
			if(achievemnt.getCourses().getId() == courseId) return;
		}
		Achievement achievement = new Achievement();
		achievement.setCertificateName(course.getCourseTitle() + " CERTIFICATE");
		achievement.setTitle(course.getCourseTitle());
		achievement.setReceivedAt(Timestamp.valueOf(LocalDateTime.now()));
		achievement.setUsers(user);
		achievement.setCourses(course);
		achievementRepository.save(achievement);
	}

	@Override
	public List<Achievement> getAllAchievement() {
		return achievementRepository.findAll();
	}

	@Override
	public Achievement getAchievementById(int achievementId) {
		return achievementRepository.findById(achievementId).orElse(null);
	}

}
