package com.g3.elis.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class FileStorageConfig {
	
//  BLOG POST FILE STORAGE CONFIGURATION STARTS HERE	
	
	@Value("${file.upload-dir}")
	private String uploadBlogImageDir;
	
	
	public Path getBlogImageUploadDir() {
		return Paths.get(uploadBlogImageDir + "/blog/blog-images").toAbsolutePath().normalize();
	}

	public void saveBlogImage(MultipartFile file, String fileName) throws IOException {
		Path targetLocation = getBlogImageUploadDir().resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation);
	}

	public void deleteBlogImage(String fileName) throws IOException {
		Path filePath = getBlogImageUploadDir().resolve(fileName).normalize();
		Files.deleteIfExists(filePath);
	}
//  BLOG POST FILE STORAGE CONFIGURATION ENDS HERE
	
	
//  HTML FILES READ WRITE CONFIGURATION STARTS HERE
	public void saveHTMLFile(String content, String path, String fileName) throws IOException {
		Path targetLocation = getUploadDir(path).resolve(fileName);
		Files.write(targetLocation, content.getBytes());
	}
	
	public String readFileContent(String fileName, String path) throws IOException {
		Path filePath = getUploadDir(path).resolve(fileName);
		return Files.readString(filePath);
	}
//  HTML FILES READ WRITE CONFIGURATION ENDS HERE
	

//  GENERAL FILES READ WRITE DELETE OPREATION STARTS HERE

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	
	public Path getUploadDir(String filePath) {
		return Paths.get(uploadDir + filePath).toAbsolutePath().normalize();
	}
	
	public String saveFile(MultipartFile file, String filePath) throws IOException {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mmss").format(new Date());
		String newFileName = timestamp + "_" + file.getOriginalFilename();
		Path targetLocation = getUploadDir(filePath).resolve(newFileName);
		Files.copy(file.getInputStream(), targetLocation);

		return newFileName;
	}
	
	public void deleteFile(String fileName, String filePath) throws IOException {
		Path targetLocation = getUploadDir(filePath).resolve(fileName);
		Files.delete(targetLocation);
	}
//  GENERAL FILES READ WRITE DELETE OPREATION STARTS ENDS
}
