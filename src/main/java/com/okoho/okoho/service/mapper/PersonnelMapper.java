package com.okoho.okoho.service.mapper;

import com.okoho.okoho.domain.*;
import com.okoho.okoho.service.dto.PersonnelDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Personnel} and its DTO {@link PersonnelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonnelMapper extends EntityMapper<PersonnelDTO, Personnel> {

}
