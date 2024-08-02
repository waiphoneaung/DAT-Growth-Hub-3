package com.g3.elis.util;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	public static final Logger LOG = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LOG.warn("User: " + auth.getName() 
            + " attempted to access the protected URL: "
            + request.getRequestURI());
		response.sendRedirect("/auth/access-denied");
	}
}