package com.okoho.okoho.repository;

import com.okoho.okoho.domain.Personnel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Personnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonnelRepository extends MongoRepository<Personnel, String> {
    @Query("{}")
    Page<Personnel> findAllWithEagerRelationships(Pageable pageable);
}
