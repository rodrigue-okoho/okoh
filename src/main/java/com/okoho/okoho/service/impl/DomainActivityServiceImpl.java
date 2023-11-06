package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.DomainActivity;
import com.okoho.okoho.repository.DomainActivityRepository;
import com.okoho.okoho.service.DomainActivityService;
import com.okoho.okoho.service.dto.DomainActivityDTO;
import com.okoho.okoho.service.mapper.DomainActivityMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link DomainActivity}.
 */
@Service
public class DomainActivityServiceImpl implements DomainActivityService {

    private final Logger log = LoggerFactory.getLogger(DomainActivityServiceImpl.class);

    private final DomainActivityRepository domainActivityRepository;

    private final DomainActivityMapper domainActivityMapper;

    public DomainActivityServiceImpl(DomainActivityRepository domainActivityRepository, DomainActivityMapper domainActivityMapper) {
        this.domainActivityRepository = domainActivityRepository;
        this.domainActivityMapper = domainActivityMapper;
    }

    @Override
    public DomainActivityDTO save(DomainActivityDTO domainActivityDTO) {
        log.debug("Request to save DomainActivity : {}", domainActivityDTO);
        DomainActivity domainActivity = domainActivityMapper.toEntity(domainActivityDTO);
        domainActivity = domainActivityRepository.save(domainActivity);
        return domainActivityMapper.toDto(domainActivity);
    }

    @Override
    public Optional<DomainActivityDTO> partialUpdate(DomainActivityDTO domainActivityDTO) {
        log.debug("Request to partially update DomainActivity : {}", domainActivityDTO);

        return domainActivityRepository
            .findById(domainActivityDTO.getId())
            .map(
                existingDomainActivity -> {
                    domainActivityMapper.partialUpdate(existingDomainActivity, domainActivityDTO);

                    return existingDomainActivity;
                }
            )
            .map(domainActivityRepository::save)
            .map(domainActivityMapper::toDto);
    }

    @Override
    public Page<DomainActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DomainActivities");
        return domainActivityRepository.findAll(pageable).map(domainActivityMapper::toDto);
    }

    @Override
    public Optional<DomainActivityDTO> findOne(String id) {
        log.debug("Request to get DomainActivity : {}", id);
        return domainActivityRepository.findById(id).map(domainActivityMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete DomainActivity : {}", id);
        domainActivityRepository.deleteById(id);
    }
}
