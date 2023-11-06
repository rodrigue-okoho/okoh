package com.okoho.okoho.repository;

import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.FileUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileUrlRepository  extends MongoRepository<FileUrl, String> {
}
