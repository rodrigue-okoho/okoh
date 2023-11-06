package com.okoho.okoho.service.mapper;

import com.okoho.okoho.domain.*;
import com.okoho.okoho.service.dto.OfferJobDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OfferJob} and its DTO {@link OfferJobDTO}.
 */
@Mapper(componentModel = "spring", uses = { RecruteurMapper.class })
public interface OfferJobMapper extends EntityMapper<OfferJobDTO, OfferJob> {

    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<OfferJobDTO> toDtoIdSet(Set<OfferJob> offerJob);
}
