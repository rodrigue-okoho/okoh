package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.Competence;
import com.okoho.okoho.repository.CompetenceRepository;
import com.okoho.okoho.service.CompetenceService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Competence}.
 */
@Service
public class CompetenceServiceImpl implements CompetenceService {

    private final Logger log = LoggerFactory.getLogger(CompetenceServiceImpl.class);

    private final CompetenceRepository competenceRepository;

    public CompetenceServiceImpl(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    @Override
    public Competence save(Competence competence) {
        log.debug("Request to save Competence : {}", competence);
        return competenceRepository.save(competence);
    }

    @Override
    public Optional<Competence> partialUpdate(Competence competence) {
        log.debug("Request to partially update Competence : {}", competence);

        return competenceRepository
            .findById(competence.getId())
            .map(
                existingCompetence -> {
                    if (competence.getLibelle() != null) {
                        existingCompetence.setLibelle(competence.getLibelle());
                    }
                    if (competence.getDescription() != null) {
                        existingCompetence.setDescription(competence.getDescription());
                    }

                    return existingCompetence;
                }
            )
            .map(competenceRepository::save);
    }

    @Override
    public List<Competence> findAll() {
        log.debug("Request to get all Competences");
        return competenceRepository.findAll();
    }

    @Override
    public Optional<Competence> findOne(String id) {
        log.debug("Request to get Competence : {}", id);
        return competenceRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Competence : {}", id);
        competenceRepository.deleteById(id);
    }
}
