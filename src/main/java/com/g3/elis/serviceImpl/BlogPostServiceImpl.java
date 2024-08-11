package com.g3.elis.serviceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.g3.elis.config.FileStorageConfig;
import com.g3.elis.dto.form.BlogPostDto;
import com.g3.elis.model.BlogPost;
import com.g3.elis.repository.BlogPostRepository;
import com.g3.elis.service.BlogPostService;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private FileStorageConfig fileStorageConfig;

    private final String BLOG_IMAGE_PATH = "/blog/blog-images";
    private final String BLOG_HTML_PATH = "/blog/blog-files";

    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost findById(int id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    @Override
    public void saveBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException {
        BlogPost blogPost = new BlogPost();

        // Handle HTML file
        if (blogPostDto.getId() > 0) {
            BlogPost existingBlogPost = blogPostRepository.findById(blogPostDto.getId()).orElse(null);
            if (existingBlogPost != null) {
                blogPost = existingBlogPost;
                blogPost.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            }
        } else {
            String fileName = UUID.randomUUID().toString() + ".html";
            blogPostDto.setHtmlFileName(fileName);
            blogPost.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }

        fileStorageConfig.saveHTMLFile(content, BLOG_HTML_PATH, blogPostDto.getHtmlFileName());
        blogPost.setHtmlFileName(blogPostDto.getHtmlFileName());
        blogPost.setTitle(blogPostDto.getTitle());
        blogPost.setUsers(blogPostDto.getUsers());

        // Handle image file
        if (imgFile != null && !imgFile.isEmpty()) {
            String newImageFileName = generateNewFileName(imgFile.getOriginalFilename());
            fileStorageConfig.saveBlogImage(imgFile, newImageFileName);
            blogPost.setBlogImage(newImageFileName);
        }

        blogPostRepository.save(blogPost);
    }

    @Override
    public void deleteBlogPost(int id) throws IOException {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);
        if (blogPostOptional.isPresent()) {
            BlogPost blogPost = blogPostOptional.get();

            // Delete associated image
            if (blogPost.getBlogImage() != null && !blogPost.getBlogImage().isEmpty()) {
                fileStorageConfig.deleteBlogImage(blogPost.getBlogImage());
            }

            // Delete associated HTML file
            if (blogPost.getHtmlFileName() != null && !blogPost.getHtmlFileName().isEmpty()) {
                fileStorageConfig.deleteFile(blogPost.getHtmlFileName(), BLOG_HTML_PATH);
            }

            // Delete the blog post from the database
            blogPostRepository.delete(blogPost);
        } else {
            throw new RuntimeException("Blog post not found with id " + id);
        }
    }

    @Override
    public void updateBlogPost(BlogPostDto blogPostDto, String content, MultipartFile imgFile) throws IOException {
        Optional<BlogPost> existingBlogPostOpt = blogPostRepository.findById(blogPostDto.getId());
        if (existingBlogPostOpt.isPresent()) {
            BlogPost blogPost = existingBlogPostOpt.get();
            blogPost.setTitle(blogPostDto.getTitle());
            blogPost.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            // Handle HTML file update
            fileStorageConfig.saveHTMLFile(content, BLOG_HTML_PATH, blogPost.getHtmlFileName());

            // Handle image file update
            if (imgFile != null && !imgFile.isEmpty()) {
                // Delete the old image if a new one is uploaded
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
    public String getBlogPostContent(BlogPost blogPost) throws IOException {
        return fileStorageConfig.readFileContent(blogPost.getHtmlFileName(), BLOG_HTML_PATH);
    }

    private String generateNewFileName(String originalFileName) {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        return dateTime + "_" + originalFileName;
    }
}
