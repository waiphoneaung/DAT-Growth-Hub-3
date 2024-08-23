package com.g3.elis.service;

import java.util.List;

import com.g3.elis.model.UserLog;


public interface UserLogService {
	
    public void logLogin(int userId);
    public void logLogout(int userId);
    public List<UserLog> getUserLogs();
}
