package com.okoho.okoho.service;

import com.okoho.okoho.service.dto.AdressDTO;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.okoho.okoho.domain.Address;

public interface AddressService {
    Address save(AdressDTO address);
    Page<Address> findAllWithEagerRelationships(Pageable pageable);
}
