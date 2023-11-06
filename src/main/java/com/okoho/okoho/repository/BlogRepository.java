package com.okoho.okoho.repository;

import com.okoho.okoho.domain.Blog;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.CategoryJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository  extends MongoRepository<Blog, String> {
    @Query("{}")
    Page<Blog> findAllWithEagerRelationships(Pageable pageable);
}
