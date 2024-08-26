//package com.g3.elis.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
//	
//    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessListener.class);
//
//	@Override
//	public void onApplicationEvent(AuthenticationSuccessEvent event) {
//		// TODO Auto-generated method stub
//		Authentication authentication = event.getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        
//        logger.info("User Logged In: " + userDetails.getUsername());
//
//	}
//
//}
