package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.ERole;
import com.okoho.okoho.domain.Personnel;
import com.okoho.okoho.domain.UserAccount;
import com.okoho.okoho.repository.PersonnelRepository;
import com.okoho.okoho.repository.RoleRepository;
import com.okoho.okoho.repository.UserAccountRepository;
import com.okoho.okoho.service.PersonnelService;
import com.okoho.okoho.service.dto.PersonnelDDTO;
import com.okoho.okoho.service.dto.PersonnelDTO;
import com.okoho.okoho.service.mapper.PersonnelMapper;
import com.okoho.okoho.utils.Constant;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Personnel}.
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    private final Logger log = LoggerFactory.getLogger(PersonnelServiceImpl.class);

    private final PersonnelRepository personnelRepository;
    private final UserAccountRepository userAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final PersonnelMapper personnelMapper;
    @Autowired
    private RoleRepository roleRepository;
    public PersonnelServiceImpl(PersonnelRepository personnelRepository,UserAccountRepository userAccountRepository,
     PersonnelMapper personnelMapper) {
        this.personnelRepository = personnelRepository;
        this.personnelMapper = personnelMapper;
        this.userAccountRepository=userAccountRepository;
    }

    @Override
    public PersonnelDTO save(PersonnelDDTO personnelDTO) {
        log.debug("Request to save Personnel : {}", personnelDTO);
        Personnel personnel=new Personnel(); 
        UserAccount userAccount=new UserAccount(); 
        if(personnelDTO.getId()!=null){
            var cat=personnelRepository.findById(personnelDTO.getId());
            personnel=cat.get();  
            userAccount=personnel.getUserAccount();
           
        }else{
            userAccount.setUserType(Constant.PERSONNALACCOUNT);
            userAccount.getRoles().add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
            userAccount.setPassword(passwordEncoder.encode("123456789"));
            userAccount.setActivated(true);
            userAccountRepository.save(userAccount);
            personnel.setUserAccount(userAccount);
        }
        userAccount.setAddress(personnelDTO.getAddress());
        userAccount.setCodePhone(personnelDTO.getCodePhone());
        userAccount.setLastName(personnelDTO.getLastName());
        userAccount.setPhoneNumber(personnelDTO.getPhoneNumber());
        userAccount.setFirstName(personnelDTO.getFirstName());
        userAccount.setEmail(personnelDTO.getEmail());
        userAccountRepository.save(userAccount);
        personnel = personnelRepository.save(personnel);
        return personnelMapper.toDto(personnel);
    }

    @Override
    public Optional<PersonnelDTO> partialUpdate(PersonnelDTO personnelDTO) {
        log.debug("Request to partially update Personnel : {}", personnelDTO);

        return personnelRepository
            .findById(personnelDTO.getId())
            .map(
                existingPersonnel -> {
                    personnelMapper.partialUpdate(existingPersonnel, personnelDTO);

                    return existingPersonnel;
                }
            )
            .map(personnelRepository::save)
            .map(personnelMapper::toDto);
    }

    @Override
    public List<PersonnelDTO> findAll() {
        log.debug("Request to get all Personnel");
        return personnelRepository.findAll().stream().map(personnelMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<PersonnelDTO> findOne(String id) {
        log.debug("Request to get Personnel : {}", id);
        return personnelRepository.findById(id).map(personnelMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Personnel : {}", id);
        personnelRepository.deleteById(id);
    }

    @Override
    public Page<Personnel> findAllWithEagerRelationships(Pageable pageable) {
      return personnelRepository.findAllWithEagerRelationships(pageable);
    }
}
