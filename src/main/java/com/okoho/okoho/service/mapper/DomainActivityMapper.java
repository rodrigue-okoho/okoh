package com.okoho.okoho.service.mapper;

import com.okoho.okoho.domain.*;
import com.okoho.okoho.service.dto.DomainActivityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DomainActivity} and its DTO {@link DomainActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DomainActivityMapper extends EntityMapper<DomainActivityDTO, DomainActivity> {}
