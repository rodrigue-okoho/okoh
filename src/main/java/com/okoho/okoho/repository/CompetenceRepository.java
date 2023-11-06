package com.okoho.okoho.repository;

import com.okoho.okoho.domain.Competence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Competence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetenceRepository extends MongoRepository<Competence, String> {
    @Query("{}")
    List<Competence> findAllWithEagerRelationships();
}
