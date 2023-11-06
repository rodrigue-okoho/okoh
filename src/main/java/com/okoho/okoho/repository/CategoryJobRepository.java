package com.okoho.okoho.repository;

import com.okoho.okoho.domain.CategoryJob;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Candidat entity.
 */
@Repository
public interface CategoryJobRepository extends MongoRepository<CategoryJob, String> {
    @Query("{}")
    Page<CategoryJob> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<CategoryJob> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<CategoryJob> findOneWithEagerRelationships(String id);
}
