package com.okoho.okoho.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.okoho.okoho.domain.ERole;
import com.okoho.okoho.domain.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
