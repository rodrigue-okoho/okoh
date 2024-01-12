package com.okoho.okoho.repository;

import com.okoho.okoho.domain.AlertJob;
import com.okoho.okoho.domain.ApplicantJob;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the DomainActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlertJobRepository extends MongoRepository<AlertJob, String> {
    @Query("{}")
    Page<AlertJob> findByUserAccount(Pageable pageable, UserAccount userAccount);
    List<AlertJob> findByUserAccount(UserAccount userAccount);
}
