package com.g3.elis.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.g3.elis.model.User;
import com.g3.elis.model.UserLog;
import com.g3.elis.repository.UserLogRepository;
import com.g3.elis.repository.UserRepository;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.service.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService{
	
	@Autowired
	public UserLogRepository userLogRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public void logLogin(int userId) {
		
		User user = userRepository.findById(userId).orElse(null);
		UserLog log = new UserLog();
		log.setUser(user);
		log.setLoginTime(LocalDateTime.now());
		userLogRepository.save(log);
	}

	@Override
	public void logLogout(int userId) {
		
		User user = userRepository.findById(userId).orElse(null);
		//UserLog log = new UserLog();
		// Find the latest UserLog entry for this user with no logoutTime
       // UserLog log = userLogRepository.findFirstByUserAndLogoutTimeIsNullOrderByLoginTimeDesc(user);
		UserLog log = userLogRepository.findTopByUserIdOrderByLoginTimeDesc(userId);
        
        if(log != null && log.getLogoutTime() == null) { // Ensure we only update the latest log without a logout time
        	log.setLogoutTime(LocalDateTime.now());
        	userLogRepository.save(log);
        }

//		log.setUser(user);
//		log.setLogoutTime(LocalDateTime.now());
//		userLogRepository.save(log);
	}

	@Override
	public List<UserLog> getUserLogs() {

		return userLogRepository.findAll();
	}

	@Override
	public List<UserLog> findLogsInLastWeek() {
		// TODO Auto-generated method stub
		LocalDateTime startOfWeek = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
		return userLogRepository.findLogsInLastWeek(startOfWeek);
	}

	@Override
	public List<UserLog> findLogsInLastYear() {
		LocalDateTime startOfMonth = LocalDateTime.now().minus(1, ChronoUnit.YEARS);
		return userLogRepository.findLogsInLastMonth(startOfMonth);
	}

	@Override
	public List<UserLog> findLogsInLastMonth() {
		LocalDateTime startOfYear = LocalDateTime.now().minus(1, ChronoUnit.MONTHS);
		return userLogRepository.findLogsInLastYear(startOfYear);
	}


}
