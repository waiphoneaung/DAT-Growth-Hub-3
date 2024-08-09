package com.g3.elis.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<BlogPost> getAllBlogPosts() {

		return blogPostRepository.findAll();
	}

	@Override
	public void saveBlogPost(BlogPostDto blogPostDto) throws IOException
	{

		BlogPost blogPost = new BlogPost();
		blogPost.setTitle(blogPostDto.getTitle());
		//blogPost.setDescription(blogPostDto.getDescription());
		blogPost.setHtmlFileName(blogPostDto.getHtmlFileName());
		blogPost.setCreatedAt(blogPostDto.getCreatedAt());
		blogPost.setUpdatedAt(blogPostDto.getUpdatedAt());
		blogPost.setBlogImage(blogPostDto.getImageFile());
		blogPost.setUsers(blogPostDto.getUsers());
		
		//MultipartFile image = blogPostDto.getImageFile();
		//String originalFileName = image.getOriginalFilename();
		
		//fileStorageConfig.saveBlogImage(image, image.getOriginalFilename());
		
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
	            fileStorageConfig.deleteFile(imageFileName,fileUploadDir);
	        }

	        // Delete the blog post from the database
	        blogPostRepository.delete(blogPost);
	    } else {
	        throw new RuntimeException("Blog post not found with id " + id);
	    }
	}


}
