package com.g3.elis.logController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.g3.elis.model.UserLog;
import com.g3.elis.service.UserLogService;

@Controller
public class UserActivityController {
	@Autowired 
	private UserLogService userLogService;
	
    @GetMapping("/admin/user-activity-log")
    public String viewUserActivity(Model model) {
       
    	List<UserLog> logs = userLogService.getUserLogs();
    	model.addAttribute("logs", logs);
    	return "admin/user-activity";
    }

}
