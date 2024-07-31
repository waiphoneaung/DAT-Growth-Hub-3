package com.g3.elis.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/**")
	             .addResourceLocations("classpath:/static/")
	             .setCachePeriod(3600)
	             .resourceChain(true)
	             .addResolver(new PathResourceResolver() {
	                    @Override
	                    protected Resource getResource(String resourcePath, Resource location) throws IOException 
	                    {
	                        Resource requestedResource = location.createRelative(resourcePath);
	                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/user/error404.html");
	                    }
	             });
	 }
	 @Override
	 public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		 configurer.defaultContentType(MediaType.ALL);
	 }
}