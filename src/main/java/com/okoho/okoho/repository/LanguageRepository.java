package com.okoho.okoho.repository;

import com.okoho.okoho.domain.Languages;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends MongoRepository<Languages, String> {
}
