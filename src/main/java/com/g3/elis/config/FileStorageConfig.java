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
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public Path getUploadDir(String filePath)
	{
		return Paths.get(uploadDir + filePath).toAbsolutePath().normalize();
	}
	
	public String saveFile(MultipartFile file,String fileName,String filePath) throws IOException
	{
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String newFileName = timestamp + "_" + fileName;
		    Path targetLocation = getUploadDir(filePath).resolve(newFileName);
		    Files.copy(file.getInputStream(), targetLocation);
		
		return newFileName;
	}
	
	public void deleteFile(String fileName,String filePath) throws IOException
	{
		Path targetLocation = getUploadDir(filePath).resolve(fileName);
		Files.delete(targetLocation);
	}
	
	public void saveHTMLFile(String content,String path,String fileName) throws IOException
	{
		Path targetLocation = getUploadDir(path).resolve(fileName);
		Files.write(targetLocation, content.getBytes());
	}
	 public String readFileContent(String fileName, String path) throws IOException {
	        Path filePath = getUploadDir(path).resolve(fileName);
	        return Files.readString(filePath);
	    }
	
}
