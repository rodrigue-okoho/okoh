package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.Blog;
import com.okoho.okoho.domain.CommentBlog;
import com.okoho.okoho.domain.FileUrl;
import com.okoho.okoho.repository.BlogRepository;
import com.okoho.okoho.repository.CommentBlogRepository;
import com.okoho.okoho.repository.FileUrlRepository;
import com.okoho.okoho.service.BlogService;
import com.okoho.okoho.service.FileService;
import com.okoho.okoho.service.dto.BlogDTO;
import com.okoho.okoho.service.dto.CommentBlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    public BlogRepository blogRepository;
    @Autowired
    public CommentBlogRepository commentBlogRepository;
    @Autowired
    public FileService fileService;
    @Autowired
    public FileUrlRepository fileUrlRepository;
    @Value("${base.domain.file}")
    private String domain;
    @Override
    public Blog save(BlogDTO blogDTO) {
        var blog=new Blog();
        blog.setDescription(blogDTO.getDescription());
        blog.setTitle(blogDTO.getTitle());
        if (blogDTO.getImage() !=null){
            var photo = fileService.convertImage(blogDTO.getImage());
            var fileurl = new FileUrl();
            fileurl.setName(blogDTO.getTitle());
            fileurl.setUrl(photo);
            fileurl.setDomain(domain + "images/");
            blog.setImage(fileUrlRepository.save(fileurl));
        }
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Page<Blog> findAllWithEagerRelationships(Pageable pageable) {
        return blogRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    public CommentBlog saveComment(CommentBlogDTO commentBlogDTO) {
        var comment=new CommentBlog();
        comment.setBlog(blogRepository.findById(commentBlogDTO.getBlog_id()).get());
        comment.setEmail(comment.getEmail());
        comment.setMessage(commentBlogDTO.getMessage());
        comment.setName(commentBlogDTO.getName());

        return commentBlogRepository.save(comment);
    }

    @Override
    public Page<CommentBlog> findAllCommentWithEagerRelationships(String blog_id, Pageable pageable) {
        return commentBlogRepository.findByBlog(pageable,blogRepository.findById(blog_id).get());
    }

    @Override
    public Optional<Blog> findOne(String id) {
        return blogRepository.findById(id);
    }

    @Override
    public Optional<CommentBlog> findCommentOne(String id) {
        return commentBlogRepository.findById(id);
    }
}
