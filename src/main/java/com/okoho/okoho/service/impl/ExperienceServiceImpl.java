package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.Experience;
import com.okoho.okoho.repository.ExperienceRepository;
import com.okoho.okoho.service.ExperienceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Experience}.
 */
@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final Logger log = LoggerFactory.getLogger(ExperienceServiceImpl.class);

    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public Experience save(Experience experience) {
        log.debug("Request to save Experience : {}", experience);
        return experienceRepository.save(experience);
    }

    @Override
    public Optional<Experience> partialUpdate(Experience experience) {
        log.debug("Request to partially update Experience : {}", experience);

        return experienceRepository
            .findById(experience.getId())
            .map(
                existingExperience -> {
                    if (experience.getIntitule() != null) {
                        existingExperience.setIntitule(experience.getIntitule());
                    }
                    if (experience.getDateBegin() != null) {
                        existingExperience.setDateBegin(experience.getDateBegin());
                    }
                    if (experience.getDateEnd() != null) {
                        existingExperience.setDateEnd(experience.getDateEnd());
                    }
                    if (experience.getPoste() != null) {
                        existingExperience.setPoste(experience.getPoste());
                    }
                    if (experience.getDescription() != null) {
                        existingExperience.setDescription(experience.getDescription());
                    }

                    return existingExperience;
                }
            )
            .map(experienceRepository::save);
    }

    @Override
    public Page<Experience> findAll(Pageable pageable) {
        log.debug("Request to get all Experiences");
        return experienceRepository.findAll(pageable);
    }

    @Override
    public Optional<Experience> findOne(String id) {
        log.debug("Request to get Experience : {}", id);
        return experienceRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Experience : {}", id);
        experienceRepository.deleteById(id);
    }
}
