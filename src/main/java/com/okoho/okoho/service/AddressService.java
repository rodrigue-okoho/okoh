package com.okoho.okoho.service;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.okoho.okoho.domain.Address;

public interface AddressService {
    Address save(Address address);
    Page<Address> findAllWithEagerRelationships(Pageable pageable);
}
