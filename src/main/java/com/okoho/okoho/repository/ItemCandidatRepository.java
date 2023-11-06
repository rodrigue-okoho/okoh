package com.okoho.okoho.repository;

import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.FileUrl;
import com.okoho.okoho.domain.ItemCandidat;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemCandidatRepository  extends MongoRepository<ItemCandidat, String> {
}
