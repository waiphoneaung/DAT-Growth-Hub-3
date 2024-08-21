package com.g3.elis.serviceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;
import com.g3.elis.repository.BlogPostRepository;
import com.g3.elis.service.BlogPostService;

@Service
public class BlogPostServiceImpl implements BlogPostService {

	private final String fileUploadDir = "/blog/blog-images";

	@Autowired
	private BlogPostRepository blogPostRepository;

	@Autowired
	private FileStorageConfig fileStorageConfig;

	@Override
	public void saveBlogPost(BlogPostDto blogPostDto) throws IOException {

		BlogPost blogPost = new BlogPost();
		blogPost.setTitle(blogPostDto.getTitle());
		// blogPost.setDescription(blogPostDto.getDescription());
		blogPost.setHtmlFileName(blogPostDto.getHtmlFileName());
		blogPost.setCreatedAt(blogPostDto.getCreatedAt());
		blogPost.setUpdatedAt(blogPostDto.getUpdatedAt());
		blogPost.setBlogImage(blogPostDto.getImageFile());
		blogPost.setUsers(blogPostDto.getUsers());

		// MultipartFile image = blogPostDto.getImageFile();
		// String originalFileName = image.getOriginalFilename();

		// fileStorageConfig.saveBlogImage(image, image.getOriginalFilename());

		blogPostRepository.save(blogPost);

	}

	@Override
	public BlogPost findById(int id) {

		return blogPostRepository.findById(id).orElse(null);

	}

	@Override
	public void deleteBlogPost(int id) throws IOException {
		Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);
		if (blogPostOptional.isPresent()) {
			BlogPost blogPost = blogPostOptional.get();

			// Delete the image file
			String imageFileName = blogPost.getBlogImage();
			if (imageFileName != null && !imageFileName.isEmpty()) {
				fileStorageConfig.deleteFile(imageFileName, fileUploadDir);
			}

			// Delete the blog post from the database
			blogPostRepository.delete(blogPost);
		} else {
			throw new RuntimeException("Blog post not found with id " + id);
		}
	}

	// for page pagination
	@Override
	public Page<BlogPost> getPaginatedBlogPosts(int page, int size) {

		PageRequest pageable = PageRequest.of(page, size);
		return blogPostRepository.findAll(pageable);
	}

	@Override
	public void updateBlogPost(BlogPostDto blogPostDto) throws IOException {
		Optional<BlogPost> existingBlogPostOpt = blogPostRepository.findById(blogPostDto.getId());
	}
	/*
	 * 
	 * @Autowired private BlogPostRepository blogPostRepository;
	 * 
	 * @Autowired private FileStorageConfig fileStorageConfig;
	 * 
	 * public final String BLOG_IMAGE_PATH = "/blog/blog-images/"; public final
	 * String BLOG_HTML_PATH = "/blog/blog-files/";
	 * 
	 * @Override public List<BlogPost> getAllBlogPosts() { return
	 * blogPostRepository.findAll(); }
	 * 
	 * @Override public BlogPost findById(int id) { return
	 * blogPostRepository.findById(id).orElse(null); }
	 * 
	 * @Override public void saveBlogPost(BlogPostDto blogPostDto, String
	 * htmlContent, MultipartFile imgFile) throws IOException { BlogPost blogPost =
	 * new BlogPost();
	 * 
	 * if (blogPostDto.getId() > 0) { BlogPost existingBlogPost =
	 * blogPostRepository.findById(blogPostDto.getId()).orElse(null); if
	 * (existingBlogPost != null) { // blogPost = existingBlogPost;
	 * updateBlogPost(blogPostDto, htmlContent, imgFile); blogPost.setUpdatedAt(new
	 * Timestamp(System.currentTimeMillis()));
	 * 
	 * 
	 * } } else { String fileName = UUID.randomUUID().toString() + ".html";
	 * blogPostDto.setHtmlFileName(fileName); blogPost.setCreatedAt(new
	 * Timestamp(System.currentTimeMillis())); }
	 * 
	 * fileStorageConfig.saveHTMLFile(htmlContent, BLOG_HTML_PATH,
	 * blogPostDto.getHtmlFileName());
	 * blogPost.setHtmlFileName(blogPostDto.getHtmlFileName());
	 * blogPost.setTitle(blogPostDto.getTitle());
	 * blogPost.setUsers(blogPostDto.getUsers());
	 * 
	 * if (imgFile != null && !imgFile.isEmpty()) { String newImageFileName =
	 * generateNewFileName(imgFile.getOriginalFilename());
	 * fileStorageConfig.saveFile(imgFile, newImageFileName, htmlContent);
	 * blogPost.setBlogImage(newImageFileName); }
	 * 
	 * blogPostRepository.save(blogPost); }
	 * 
	 * @Override public void deleteBlogPost(int id) throws IOException {
	 * Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id); if
	 * (blogPostOptional.isPresent()) { BlogPost blogPost = blogPostOptional.get();
	 * 
	 * if (blogPost.getBlogImage() != null && !blogPost.getBlogImage().isEmpty()) {
	 * fileStorageConfig.delete(blogPost.getBlogImage()); }
	 * 
	 * if (blogPost.getHtmlFileName() != null &&
	 * !blogPost.getHtmlFileName().isEmpty()) {
	 * fileStorageConfig.deleteFile(blogPost.getHtmlFileName(), BLOG_HTML_PATH); }
	 * 
	 * blogPostRepository.delete(blogPost); } else { throw new
	 * RuntimeException("Blog post not found with id " + id); } }
	 * 
	 * @Override public void updateBlogPost(BlogPostDto blogPostDto, String
	 * htmlContent, MultipartFile imgFile) throws IOException { Optional<BlogPost>
	 * existingBlogPostOpt = blogPostRepository.findById(blogPostDto.getId());
	 * 
	 * if (existingBlogPostOpt.isPresent()) { BlogPost blogPost =
	 * existingBlogPostOpt.get(); blogPost.setTitle(blogPostDto.getTitle());
	 * blogPost.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	 * 
	 * fileStorageConfig.saveHTMLFile(htmlContent, BLOG_HTML_PATH,
	 * blogPost.getHtmlFileName());
	 * 
	 * if (imgFile != null && !imgFile.isEmpty()) {
	 * fileStorageConfig.deleteBlogImage(blogPost.getBlogImage()); String
	 * newImageFileName = generateNewFileName(imgFile.getOriginalFilename());
	 * fileStorageConfig.saveBlogImage(imgFile, newImageFileName);
	 * blogPost.setBlogImage(newImageFileName); }
	 * 
	 * blogPostRepository.save(blogPost); } else { throw new
	 * RuntimeException("Blog post not found with id " + blogPostDto.getId()); } }
	 * 
	 * @Override public void updateBlogPostStatus(int id, boolean enabled) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 */
//	@Override
//	public Page<BlogPost> searchBlogPostsByName(String name, Pageable pageable) {
//		return BlogPostRepository.findByNameContainingIgnoreCaseAndRole(name, "ROLE_STUDENT", pageable);
//	}

	@Override
	public Page<BlogPost> getAllBlogPosts(Pageable pageable) {
		// TODO Auto-generated method stub
		return blogPostRepository.findAll(pageable);
	}

	@Override
	public Page<BlogPost> searchBlogPostsByTitle(String title, Pageable pageable) {

		return blogPostRepository.findByTitleContainingIgnoreCase(title, pageable);
	}
//    @Override
//    public String getBlogPostContent(BlogPost blogPost) throws IOException {
//        return fileStorageConfig.readFileContent(blogPost.getHtmlFileName(), BLOG_HTML_PATH);
//    }

	private String generateNewFileName(String originalFileName) {
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
		return dateTime + "_" + originalFileName;
	}

	@Override
	public List<BlogPost> getAllBlogPosts() {
		return blogPostRepository.findAll();
	}

//
//	@Override
//	public void updateBlogPostStatus(int id, boolean enabled) {
//		// TODO Auto-generated method stub
//		
//	}

	// for page pagination
//	@Override
//	public Page<BlogPost> getPaginatedBlogPosts(int page, int size) {
//
//		PageRequest pageable = PageRequest.of(page, size);
//		return blogPostRepository.findAll(pageable);
//	}
//	

}