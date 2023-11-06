package com.okoho.okoho.service.mapper;

import com.okoho.okoho.domain.*;
import com.okoho.okoho.service.dto.CandidatDDTO;
import com.okoho.okoho.service.dto.CandidatDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Candidat} and its DTO {@link CandidatDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CandidatMapper extends EntityMapper<CandidatDDTO, Candidat> {}
