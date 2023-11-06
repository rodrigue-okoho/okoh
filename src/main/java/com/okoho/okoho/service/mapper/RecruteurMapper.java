package com.okoho.okoho.service.mapper;

import com.okoho.okoho.domain.*;
import com.okoho.okoho.service.dto.RecruteurDDTO;
import com.okoho.okoho.service.dto.RecruteurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Recruteur} and its DTO {@link RecruteurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecruteurMapper extends EntityMapper<RecruteurDDTO, Recruteur> {
  

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RecruteurDTO toDtoId(Recruteur recruteur);
}
