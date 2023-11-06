package com.okoho.okoho.service.mapper;

import com.okoho.okoho.domain.ERole;
import com.okoho.okoho.domain.Role;
import org.mapstruct.*;

import com.okoho.okoho.domain.UserAccount;
import com.okoho.okoho.service.dto.UserAccountDTO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link UserAccount} and its DTO {@link UserAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserAccountMapper extends EntityMapper<UserAccountDTO, UserAccount> {

}
