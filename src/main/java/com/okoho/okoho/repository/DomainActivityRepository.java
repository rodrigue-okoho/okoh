package com.okoho.okoho.repository;

import com.okoho.okoho.domain.DomainActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DomainActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DomainActivityRepository extends MongoRepository<DomainActivity, String> {}
