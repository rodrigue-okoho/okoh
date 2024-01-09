package com.okoho.okoho.service.mapper;

import com.okoho.okoho.domain.Branch;
import com.okoho.okoho.service.dto.BranchDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    Branch dtoToEntity(BranchDto dto);
    BranchDto entityToDto(Branch branch);
}
