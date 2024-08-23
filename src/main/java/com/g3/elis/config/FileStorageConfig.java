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

	@Value("${file.upload-dir}")
	private String profileImageDir;

	@Value("${file.upload-dir}")
	private String uploadBlogImageDir;

	@Value("${file.upload-dir}")
	private String uploadStudentImageDir;

	public Path getProfileImageDir() {
		return Paths.get(profileImageDir + "/profile/user-profile").toAbsolutePath().normalize();
	}

	public String saveProfileImageFile(MultipartFile file, String originalFileName) throws IOException {

		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String newFileName = timestamp + "_" + originalFileName;
		Path targetLocation = getProfileImageDir().resolve(newFileName);
		Files.copy(file.getInputStream(), targetLocation);

		return newFileName;

	}

	public void deleteProfileImageFile(String fileName) throws IOException {
		Path targetLocation = getProfileImageDir().resolve(fileName);
		Files.delete(targetLocation);
	}

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

	public Path getUploadDir() {
		return Paths.get(uploadDir).toAbsolutePath().normalize();
	}

	public void saveFile(MultipartFile file, String fileName) throws IOException {
		Path targetLocation = getUploadDir().resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation);
	}

	public void deleteFile(String fileName) throws IOException {
		Path targetLocation = getUploadDir().resolve(fileName);
		Files.delete(targetLocation);
	}

	public Path getUploadDir(String filePath) {
		return Paths.get(uploadDir + filePath).toAbsolutePath().normalize();
	}

	public String saveFile(MultipartFile file, String fileName, String filePath) throws IOException {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mmss").format(new Date());
		String newFileName = timestamp + "_" + fileName;
		Path targetLocation = getUploadDir(filePath).resolve(newFileName);
		Files.copy(file.getInputStream(), targetLocation);

		return newFileName;
	}

	public void deleteFile(String fileName, String filePath) throws IOException {
		Path targetLocation = getUploadDir(filePath).resolve(fileName);
		Files.delete(targetLocation);
	}

	public void saveHTMLFile(String content, String path, String fileName) throws IOException {
		Path targetLocation = getUploadDir(path).resolve(fileName);
		Files.write(targetLocation, content.getBytes());
	}

	public String readFileContent(String fileName, String path) throws IOException {
		Path filePath = getUploadDir(path).resolve(fileName);
		return Files.readString(filePath);
	}

}
