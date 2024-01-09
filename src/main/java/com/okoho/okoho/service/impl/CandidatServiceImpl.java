package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.*;
import com.okoho.okoho.repository.*;
import com.okoho.okoho.rest.errors.BadRequestAlertException;
import com.okoho.okoho.rest.errors.TypeAccountExeption;
import com.okoho.okoho.service.CandidatService;
import com.okoho.okoho.service.FileService;
import com.okoho.okoho.service.criteria.CandidatCriteria;
import com.okoho.okoho.service.dto.*;
import com.okoho.okoho.service.mapper.BranchMapper;
import com.okoho.okoho.service.mapper.LanguageMapper;
import com.okoho.okoho.utils.Constant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Service Implementation for managing {@link Candidat}.
 */
@Service
public class CandidatServiceImpl implements CandidatService {

    private final Logger log = LoggerFactory.getLogger(CandidatServiceImpl.class);
    @Value("${base.domain.file}")
    private String domain;
    private final FileService fileService;
    private final CandidatRepository candidatRepository;
    private final UserAccountRepository userAccountRepository;
    private final FileUrlRepository fileUrlRepository;
    private final ItemCandidatRepository itemCandidatRepository;
    private final CategoryJobRepository categoryJobRepository;
    private final AddressRepository addressRepository;
    private LanguageMapper languageMapper;
    private LanguageRepository languageRepository;
    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;

    public CandidatServiceImpl(FileService fileService,
                               UserAccountRepository userAccountRepository, ItemCandidatRepository itemCandidatRepository,
                               CandidatRepository candidatRepository, AddressRepository addressRepository,
                               FileUrlRepository fileUrlRepository,
                               CategoryJobRepository categoryJobRepository, LanguageMapper languageMapper, LanguageRepository languageRepository, BranchMapper branchMapper, BranchRepository branchRepository) {
        this.fileService = fileService;
        this.candidatRepository = candidatRepository;
        this.userAccountRepository = userAccountRepository;
        this.fileUrlRepository = fileUrlRepository;
        this.itemCandidatRepository = itemCandidatRepository;
        this.categoryJobRepository = categoryJobRepository;
        this.addressRepository = addressRepository;
        this.languageMapper = languageMapper;
        this.languageRepository = languageRepository;
        this.branchMapper = branchMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    public Candidat save(CandidatDTO registerRequest) {
        System.out.println("Request to save Candidat : {}" + registerRequest);
        log.debug("Request to save Candidat : {}", registerRequest);
        if (registerRequest.getUser_type() == null) {
            throw new TypeAccountExeption(null);
        }
        if (registerRequest.getUser_type().equals(Constant.CANDIDATACCOUNT)) {
            // UserAccount account=
            // userAccountRepository.findOneByEmailIgnoreCase(registerRequest.getEmail()).orElseThrow();
            var candidat = candidatRepository.findById(registerRequest.getId()).get();
            UserAccount account = candidat.getUserAccount();
            switch (registerRequest.getMode()) {
                case "profile":
                    account.setFirstName(registerRequest.getFirstName());
                    account.setLastName(registerRequest.getLastName());
                    account.setGender(registerRequest.getGender());
                    account.setPhoneNumber(registerRequest.getPhoneNumber());
                    account.setShow_profile(registerRequest.getShow_profile());
                    account.setCodePhone(registerRequest.getCodePhone());
                    // candidat.setAge(registerRequest.getAge());
                    candidat.setDob(LocalDate.parse(registerRequest.getDob()));
                    candidat.setPlaceofborn(registerRequest.getPlaceofborn());
                    candidat.setCountryofborn(registerRequest.getCountryofborn());
                    candidat.setCurrentSalary(registerRequest.getCurrentSalary());
                    candidat.setDescription(registerRequest.getDescription());
                    candidat.setEducationLevel(registerRequest.getEducationLevel());
                    candidat.setExpectedSalary(registerRequest.getExpectedSalary());
                    candidat.setExperienceTime(registerRequest.getExperienceTime());
                    candidat.setJobTitle(registerRequest.getJobTitle());
                    candidat.setQualification(registerRequest.getQualification());
                    candidat.setSalaryType(registerRequest.getSalaryType());
                    // registerRequest.getCategoryJobs().stream().forEach(e->candidat.addCategoryJobs(categoryJobRepository.findById(e).get()));

                    break;
                case "sociale":
                    candidat.setFacebook(registerRequest.getFacebook());
                    candidat.setTwitter(registerRequest.getTwitter());
                    candidat.setLinkedin(registerRequest.getLinkedin());
                    candidat.setGoogleplus(registerRequest.getGoogleplus());
                    break;
                case "contact":
                    candidat.setCountry(registerRequest.getCountry());
                    account.setAddress(registerRequest.getAddress());
                    account.setLatitude(registerRequest.getLatitude());
                    account.setLongitude(registerRequest.getLongitude());
                    candidat.setTown(registerRequest.getTown());
                    break;
                default:

                    if (!registerRequest.getImageUrl().isEmpty()) {
                        var f = account.getFileUrl();
                        if (f != null) {
                            fileUrlRepository.delete(f);
                        }
                       // var photo = fileService.convertImage(registerRequest.getImageUrl());
                        var fileurl = new FileUrl();
                        fileurl.setName(candidat.getUserAccount().getFirstName());
                        fileurl.setUrl(registerRequest.getImageUrl());
                        fileurl.setDomain(domain + "images/");
                        account.setFileUrl(fileUrlRepository.save(fileurl));
                        account.setImageUrl(registerRequest.getImageUrl());
                    }
                    if (registerRequest.getLangKey() == null) {
                        account.setLangKey("de"); // default language
                    } else {
                        account.setLangKey(registerRequest.getLangKey());
                    }
                    break;
            }
            userAccountRepository.save(account);
            return candidatRepository.save(candidat);
        } else {
            throw new BadRequestAlertException("Invalid user", "Candidat", "idnull");
        }

    }

    @Override
    public FileUrl uploadCv(FileUrlDTO fileUrlDTO) {
        var candidat = candidatRepository.findById(fileUrlDTO.getId()).get();

        var photo = fileService.convertDocument(fileUrlDTO.getUrl());
        var fileurl = new FileUrl();
        fileurl.setName(fileUrlDTO.getName());
        fileurl.setDomain(domain + "images/");
        fileurl.setUrl(domain + "images/" + photo);
        candidat.addCvs(fileUrlRepository.save(fileurl));
        candidatRepository.save(candidat);
        return null;
    }

    @Override
    public void removeCv(String idFile) {
        var file = fileUrlRepository.findById(idFile).get();
        fileUrlRepository.deleteById(idFile);
    }

    @Override
    public Optional<Candidat> partialUpdate(Candidat candidat) {
        log.debug("Request to partially update Candidat : {}", candidat);

        return candidatRepository
                .findById(candidat.getId())
                .map(
                        existingCandidat -> {
                            if (candidat.getCountry() != null) {
                                existingCandidat.setCountry(candidat.getCountry());
                            }
                            if (candidat.getDob() != null) {
                                existingCandidat.setDob(candidat.getDob());
                            }

                            return existingCandidat;
                        })
                .map(candidatRepository::save);
    }

    @Override
    public List<Candidat> findAll() {
        log.debug("Request to get all Candidats");
        return candidatRepository.findAllWithEagerRelationships();
    }

    public Page<Candidat> findAllWithEagerRelationships(CandidatCriteria candidatCriteria, Pageable pageable) {
        /*
         * var list=candidatRepository.findAllWithEagerRelationships()
         * .stream()
         * .filter(e-> Objects.equals(e.getExperienceTime(),
         * candidatCriteria.getExperience()))
         *
         * .collect(Collectors.toCollection(LinkedList::new));
         * return new PageImpl<>(list,pageable,list.size());
         */
        return candidatRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    public Optional<Candidat> findOne(String id) {
        log.debug("Request to get Candidat : {}", id);
        return candidatRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Candidat : {}", id);
        candidatRepository.deleteById(id);
    }

    @Override
    public Optional<Candidat> findProfile(String id) {
        var account = userAccountRepository.findById(id);
        return candidatRepository.findFirstByUserAccount(account.get());
    }

    @Override
    public List<FileUrl> findCvs(String idcandidat) {

        return candidatRepository.findById(idcandidat).get().getCvs().stream().collect(Collectors.toList());
    }

    @Override
    public ItemCandidatDTO addItemCandidat(ItemCandidatDTO itemCandidatDTO) {
        if(itemCandidatDTO == null) {
            log.error("Unable to save null entity");
        }

        if(itemCandidatDTO.getId() == null){
            var candidat = candidatRepository.findById(itemCandidatDTO.getOwner_id()).get();
            var item = new ItemCandidat();
            item.setBegin(LocalDate.parse(itemCandidatDTO.getBegin()));
            item.setEnd(LocalDate.parse(itemCandidatDTO.getEnd()));
            item.setDescription(itemCandidatDTO.getDescription());
            item.setLibelle(itemCandidatDTO.getLibelle());
            item.setLocation(itemCandidatDTO.getLocation());
            item.setCity(itemCandidatDTO.getCity());
            item.setCountry(itemCandidatDTO.getCountry());
            item.setLine1(itemCandidatDTO.getLine1());
            item.setLine2(itemCandidatDTO.getLine2());
            item.setItemType(itemCandidatDTO.getItemType());
            item.setPostcode(itemCandidatDTO.getPostcode());
            if (item.getItemType().equals("education")) {
                item.setTraning_body(itemCandidatDTO.getTraning_body());
                item.setWebsite(itemCandidatDTO.getWebsite());
                candidat.addEducation(itemCandidatRepository.save(item));
            } else if (item.getItemType().equals("work")) {
                item.setEmployer_name(itemCandidatDTO.getEmployer_name());
                candidat.addWork(itemCandidatRepository.save(item));
            } else if (item.getItemType().equals("award")) {
                candidat.addAwards(itemCandidatRepository.save(item));
            }
            candidatRepository.save(candidat);
        }
        else{
            var itemResult = itemCandidatRepository.findById(itemCandidatDTO.getId()).get();
            itemResult.setBegin(LocalDate.parse(itemCandidatDTO.getBegin()));
            itemResult.setEnd(LocalDate.parse(itemCandidatDTO.getEnd()));
            itemResult.setDescription(itemCandidatDTO.getDescription());
            itemResult.setLibelle(itemCandidatDTO.getLibelle());
            itemResult.setLocation(itemCandidatDTO.getLocation());
            itemResult.setCity(itemCandidatDTO.getCity());
            itemResult.setCountry(itemCandidatDTO.getCountry());
            itemResult.setLine1(itemCandidatDTO.getLine1());
            itemResult.setLine2(itemCandidatDTO.getLine2());
            itemResult.setPostcode(itemCandidatDTO.getPostcode());
            if (itemResult.getItemType().equals("education")) {
                itemResult.setTraning_body(itemCandidatDTO.getTraning_body());
                itemResult.setWebsite(itemCandidatDTO.getWebsite());
            } else if (itemResult.getItemType().equals("work")) {
                itemResult.setEmployer_name(itemCandidatDTO.getEmployer_name());
            }
            itemCandidatRepository.save(itemResult);
        }
        return itemCandidatDTO;
    }

    @Override
    public void removeItemCandidat(String idItem) {
        itemCandidatRepository.deleteById(idItem);
    }

    @Override
    public AdressDTO addAddress(AdressDTO adressDTO) {
        if (adressDTO.getId() == null){
            var candidat = candidatRepository.findById(adressDTO.getOwner_id()).get();
            var address=new Address();
            address.setCity(adressDTO.getCity());
            address.setCountry(adressDTO.getCountry());
            address.setPostcode(adressDTO.getPostcode());
            address.setType(adressDTO.getType());
            address.setLine1(adressDTO.getLine1());
            address.setLine2(adressDTO.getLine2());
            addressRepository.save(address);
            candidat.addAddress(address);
            candidatRepository.save(candidat);
        }else {
            var address=addressRepository.findById(adressDTO.getId()).get();
            address.setCity(adressDTO.getCity());
            address.setCountry(adressDTO.getCountry());
            address.setPostcode(adressDTO.getPostcode());
            address.setType(adressDTO.getType());
            address.setLine1(adressDTO.getLine1());
            address.setLine2(adressDTO.getLine2());
            addressRepository.save(address);
        }



        return adressDTO;
    }

    @Override
    public void removeAddress(String idItem) {
        addressRepository.deleteById(idItem);
    }
    @Override
    public void removeLanguage(String langId) {
        languageRepository.deleteById(langId);
    }

    @Override
    public LanguageDto saveLanguage(LanguageDto languageDto) {
        log.debug("Try to save language");

        if(languageDto == null) {
            log.error("Unable to save null value");
        }
        if(StringUtils.hasLength(languageDto.getOwner_id()) && languageDto.getId() == null) {
            Optional<Candidat> candidat = candidatRepository.findById(languageDto.getOwner_id());
            if(!candidat.isPresent()) {
                log.error("Unable to save this language: language[{}]", languageDto);
                throw new RuntimeException("Unable to save this lang because candidat not found");
            }
            Languages lang = languageRepository.save(languageMapper.dtoToEntity(languageDto));
            languageDto.setId(lang.getId());
            candidat.get().addLanguage(lang);
            candidatRepository.save(candidat.get());
        }
        else {
            Optional<Languages> existing = languageRepository.findById(languageDto.getId());
            if(!existing.isPresent()) {
                log.error("Unable to save this language: language[{}]", languageDto);
                throw new RuntimeException("Unable to save this lang because candidat not found");
            }
            languageRepository.save(Languages.build(existing.get(), languageDto));
        }
        log.debug("Successfully saved language: {}", languageDto);
        return languageDto;
    }

    @Override
    public BranchDto saveBranch(BranchDto branchDto) {
        log.debug("Try to save language");

        if(branchDto == null) {
            log.error("Unable to save null value");
        }
        if(StringUtils.hasLength(branchDto.getOwner_id()) && branchDto.getId() == null) {
            Optional<Candidat> candidat = candidatRepository.findById(branchDto.getOwner_id());
            if(!candidat.isPresent()) {
                log.error("Unable to save this language: language[{}]", branchDto);
                throw new RuntimeException("Unable to save this lang because candidat not found");
            }
            Branch branch = branchRepository.save(branchMapper.dtoToEntity(branchDto));
            branchDto.setId(branch.getId());
            candidat.get().addBranch(branch);
            candidatRepository.save(candidat.get());
        }
        else {
            Optional<Branch> existing = branchRepository.findById(branchDto.getId());
            if(existing.isEmpty()) {
                log.error("Unable to save this language: language[{}]", branchDto);
                throw new RuntimeException("Unable to save this lang because candidat not found");
            }
            branchRepository.save(Branch.build(existing.get(), branchDto));
        }
        log.debug("Successfully saved language: {}", branchDto);
        return branchDto;
    }

    @Override
    public void removeBranch(String langId) {
        branchRepository.deleteById(langId);
    }

    @Override
    public Page<Candidat> findSearch(Pageable pageable, String keyword, String location, String category,
                                     String dateposted, String education, String experience) {
        var items = candidatRepository.findAll();
        if (!keyword.isBlank()) {
            System.out.print(keyword);
            items = items.stream()
                    .filter(e -> e.getUserAccount().getFirstName().contains(keyword))
                    .collect(Collectors.toList());
        }
        if (!location.isBlank()) {
            System.out.print(location);
            items = items.stream()
                    .filter(e -> e.getCountry().contains(location))
                    .filter(e -> e.getTown().contains(location))
                    .collect(Collectors.toList());
        }
        if (!category.isBlank()) {
            System.out.print(category);
            items = items.stream()
                    .filter(e -> e.getCategoryJobs().stream().filter(categoryJob -> categoryJob.getTitle().contains(category)).equals(true))
                    .collect(Collectors.toList());
        }
        if (!dateposted.isBlank()) {
            items = items.stream()
                    .filter(e -> e.getDob().isBefore(LocalDate.parse(dateposted)))
                    .collect(Collectors.toList());
        }
        return new PageImpl<>(items, pageable, items.size());
    }

    @Override
    public FileUrl findCv(String idcandidat, int typecv) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCv'");
    }
}
