 package com.g3.elis.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;
import com.g3.elis.repository.BlogPostRepository;
import com.g3.elis.service.BlogPostService;

@Service
public class BlogPostServiceImpl implements BlogPostService {

	
	private final String imgUploadDir =  "/blog/blog-images/";
	public final String htmlUploadDir = "/blog/blog-files/";


	@Autowired
	private BlogPostRepository blogPostRepository;

	@Autowired
	private FileStorageConfig fileStorageConfig;


	@Override
	public BlogPost findById(int id) {

		return blogPostRepository.findById(id).orElse(null);
	}

	// for page pagination
	@Override
	public Page<BlogPost> getPaginatedBlogPosts(int page, int size) {

		PageRequest pageable = PageRequest.of(page, size);
		return blogPostRepository.findAll(pageable);
	}


		
	 @Override
	    public void saveBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException {
	        BlogPost blogPost = new BlogPost();
	            String fileName = UUID.randomUUID().toString() + ".html";
	          
	            blogPostDto.setHtmlFileName(fileName);
	            blogPost.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	       // fileStorageConfig.saveHTMLFile(content, htmlUploadDir, blogPostDto.getHtmlFileName());
	        blogPost.setHtmlFileName(blogPostDto.getHtmlFileName());
	        blogPost.setTitle(blogPostDto.getTitle());
	        blogPost.setUsers(blogPostDto.getUsers());
	        String newImageFileName = generateNewFileName(imgFile.getOriginalFilename());
	        fileStorageConfig.saveBlogImage(imgFile, newImageFileName);
	        
	        fileStorageConfig.saveHTMLFile(content,htmlUploadDir, fileName);
	        blogPost.setBlogImage(newImageFileName);
	       // Files.write(Paths.get(htmlUploadDir), content.getBytes());
	        blogPostRepository.save(blogPost);
	    }

    @Override
    public void deleteBlogPost(int id) throws IOException {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);
        if (blogPostOptional.isPresent()) {
            BlogPost blogPost = blogPostOptional.get();

            if (blogPost.getBlogImage() != null && !blogPost.getBlogImage().isEmpty()) {
                fileStorageConfig.deleteFile(blogPost.getBlogImage(), imgUploadDir);
            }

            if (blogPost.getHtmlFileName() != null && !blogPost.getHtmlFileName().isEmpty()) {
                fileStorageConfig.deleteFile(blogPost.getHtmlFileName(), htmlUploadDir);
            }

            blogPostRepository.delete(blogPost);
        } else {
            throw new RuntimeException("Blog post not found with id " + id);
        }
    }

    

	@Override
	public Page<BlogPost> getAllBlogPosts(Pageable pageable) {
		// TODO Auto-generated method stub
		return blogPostRepository.findAll(pageable);
	}

	@Override
	public Page<BlogPost> searchBlogPostsByTitle(String title, Pageable pageable) {

		return blogPostRepository.findByTitleContainingIgnoreCase(title, pageable);
	}

	private String generateNewFileName(String originalFileName) {
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
		return dateTime + "_" + originalFileName;
	}

	@Override
	public List<BlogPost> getAllBlogPosts() {
		return blogPostRepository.findAll();
	}


	@Override
    public void updateBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException {
        Optional<BlogPost> existingBlogPostOpt = blogPostRepository.findById(blogPostDto.getId());
        if (existingBlogPostOpt.isPresent()) {
            BlogPost blogPost = existingBlogPostOpt.get();
            blogPost.setTitle(blogPostDto.getTitle());
            blogPost.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            fileStorageConfig.saveHTMLFile(content, htmlUploadDir, blogPost.getHtmlFileName());

            if (imgFile != null && !imgFile.isEmpty()) {
                fileStorageConfig.deleteBlogImage(blogPost.getBlogImage());
                String newImageFileName = generateNewFileName(imgFile.getOriginalFilename());
                fileStorageConfig.saveBlogImage(imgFile, newImageFileName);
                blogPost.setBlogImage(newImageFileName);
            }

            blogPostRepository.save(blogPost);
        } else {
            throw new RuntimeException("Blog post not found with id " + blogPostDto.getId());
        }
    }

	@Override
	public Page<BlogPost> searchBlogPostsByKeyword(String keyword, Pageable pageable) {
		
		    return blogPostRepository.findByTitleContainingIgnoreCase(keyword, pageable);
		

	}

	
		
}