package com.okoho.okoho.repository;

import com.okoho.okoho.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentBlogRepository  extends MongoRepository<CommentBlog, String> {
    @Query("{}")
    Page<CommentBlog> findByBlog(Pageable pageable, Blog blog);

}
