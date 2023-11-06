package com.okoho.okoho.service;

import com.okoho.okoho.domain.Blog;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.CommentBlog;
import com.okoho.okoho.service.criteria.CandidatCriteria;
import com.okoho.okoho.service.dto.BlogDTO;
import com.okoho.okoho.service.dto.CandidatDTO;
import com.okoho.okoho.service.dto.CommentBlogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService {
    Blog save(BlogDTO blogDTO);
    Page<Blog> findAllWithEagerRelationships(Pageable pageable);
    CommentBlog saveComment(CommentBlogDTO commentBlogDTO);
    Page<CommentBlog> findAllCommentWithEagerRelationships(String blog_id,Pageable pageable);
    Optional<Blog> findOne(String id);
    Optional<CommentBlog> findCommentOne(String id);
}
