package com.g3.elis.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class FileStorageConfig {
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Value("${file.upload-dir}")
	private String uploadBlogImageDir;
	
	public Path getBlogImageUploadDir()
	{
		return Paths.get(uploadBlogImageDir + "/blog/blog-images").toAbsolutePath().normalize();
	}
	
	public void saveBlogImage(MultipartFile file,String fileName) throws IOException
	{
		Path targetLocation = getBlogImageUploadDir().resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation);
	}
	
//	public void deleteBlogImage(String blogFileName) throws IOException {
//		
//		Path targetLocation = getBlogImageUploadDir().resolve(blogFileName);
//		Files.delete(targetLocation);
//	}
	
	public void deleteBlogImage(String fileName) throws IOException {
	    Path filePath = getBlogImageUploadDir().resolve(fileName).normalize();
	    Files.deleteIfExists(filePath);
	}

	
	
	public Path getUploadDir()
	{
		return Paths.get(uploadDir).toAbsolutePath().normalize();
	}
	
	public void saveFile(MultipartFile file,String fileName) throws IOException
	{
		Path targetLocation = getUploadDir().resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation);
	}
	public void deleteFile(String fileName) throws IOException
	{
		Path targetLocation = getUploadDir().resolve(fileName);
		Files.delete(targetLocation);
	}
}