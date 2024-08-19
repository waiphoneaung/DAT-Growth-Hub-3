package com.g3.elis.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClearSuperDtoInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception
	{
		if(handler instanceof HandlerMethod)
		{
			String requestURI = request.getRequestURI();
			// Clear the superDto session attribute if not within the /admin/admin-create-course paths
			if(!requestURI.startsWith("/admin/create-course"))
			{
				request.getSession().removeAttribute("superDto");
			}
		}
		return true;
	}
}
