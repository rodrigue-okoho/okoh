package com.okoho.okoho.service.mapper;

import com.okoho.okoho.domain.Languages;
import com.okoho.okoho.service.dto.LanguageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    Languages dtoToEntity(LanguageDto dto);
    LanguageDto entityToDto(Languages language);
}
