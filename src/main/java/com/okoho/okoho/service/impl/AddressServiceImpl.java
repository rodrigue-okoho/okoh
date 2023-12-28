package com.okoho.okoho.service.impl;
import com.okoho.okoho.service.dto.AdressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.okoho.okoho.domain.Address;
import com.okoho.okoho.repository.AddressRepository;
import com.okoho.okoho.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address save(AdressDTO address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Page<Address> findAllWithEagerRelationships(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllWithEagerRelationships'");
    }
}