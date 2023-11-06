package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.Personnel;
import com.okoho.okoho.repository.PersonnelRepository;
import com.okoho.okoho.service.PersonnelService;
import com.okoho.okoho.service.dto.PersonnelDTO;
import com.okoho.okoho.service.mapper.PersonnelMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Personnel}.
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    private final Logger log = LoggerFactory.getLogger(PersonnelServiceImpl.class);

    private final PersonnelRepository personnelRepository;

    private final PersonnelMapper personnelMapper;

    public PersonnelServiceImpl(PersonnelRepository personnelRepository, PersonnelMapper personnelMapper) {
        this.personnelRepository = personnelRepository;
        this.personnelMapper = personnelMapper;
    }

    @Override
    public PersonnelDTO save(PersonnelDTO personnelDTO) {
        log.debug("Request to save Personnel : {}", personnelDTO);
        Personnel personnel = personnelMapper.toEntity(personnelDTO);
        personnel = personnelRepository.save(personnel);
        return personnelMapper.toDto(personnel);
    }

    @Override
    public Optional<PersonnelDTO> partialUpdate(PersonnelDTO personnelDTO) {
        log.debug("Request to partially update Personnel : {}", personnelDTO);

        return personnelRepository
            .findById(personnelDTO.getId())
            .map(
                existingPersonnel -> {
                    personnelMapper.partialUpdate(existingPersonnel, personnelDTO);

                    return existingPersonnel;
                }
            )
            .map(personnelRepository::save)
            .map(personnelMapper::toDto);
    }

    @Override
    public List<PersonnelDTO> findAll() {
        log.debug("Request to get all Personnel");
        return personnelRepository.findAll().stream().map(personnelMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<PersonnelDTO> findOne(String id) {
        log.debug("Request to get Personnel : {}", id);
        return personnelRepository.findById(id).map(personnelMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Personnel : {}", id);
        personnelRepository.deleteById(id);
    }
}
