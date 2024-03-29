package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.FileUrl;
import com.okoho.okoho.domain.Recruteur;
import com.okoho.okoho.domain.UserAccount;
import com.okoho.okoho.repository.CategoryJobRepository;
import com.okoho.okoho.repository.FileUrlRepository;
import com.okoho.okoho.repository.RecruteurRepository;
import com.okoho.okoho.repository.UserAccountRepository;
import com.okoho.okoho.rest.errors.BadRequestAlertException;
import com.okoho.okoho.rest.errors.TypeAccountExeption;
import com.okoho.okoho.service.FileService;
import com.okoho.okoho.service.RecruteurService;
import com.okoho.okoho.service.dto.RecruteurDDTO;
import com.okoho.okoho.service.dto.RecruteurDTO;
import com.okoho.okoho.service.mapper.RecruteurMapper;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okoho.okoho.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Recruteur}.
 */
@Service
public class RecruteurServiceImpl implements RecruteurService {

    private final Logger log = LoggerFactory.getLogger(RecruteurServiceImpl.class);
    @Value("${base.domain.file}")
    private String domain;
    private final FileService fileService;
    private final RecruteurRepository recruteurRepository;
    private final UserAccountRepository userAccountRepository;
    private final RecruteurMapper recruteurMapper;
    private final FileUrlRepository fileUrlRepository;
    private final CategoryJobRepository categoryJobRepository;
    public RecruteurServiceImpl(FileService fileService, RecruteurRepository recruteurRepository, UserAccountRepository userAccountRepository, RecruteurMapper recruteurMapper, FileUrlRepository fileUrlRepository, CategoryJobRepository categoryJobRepository) {
        this.fileService = fileService;
        this.recruteurRepository = recruteurRepository;
        this.userAccountRepository = userAccountRepository;
        this.recruteurMapper = recruteurMapper;
        this.fileUrlRepository = fileUrlRepository;
        this.categoryJobRepository = categoryJobRepository;
    }

    @Override
    public Recruteur save(RecruteurDTO registerRequest) {
        System.out.println(registerRequest.getCoverUrl());
        log.debug("Request to save Recruteur : {}", registerRequest);
        if (registerRequest.getUser_type() == null) {
            throw new TypeAccountExeption(null);
        }
        if (registerRequest.getUser_type().equals(Constant.ENTREPRISEACCOUNT)) {
          var entreprise = recruteurRepository.findById(registerRequest.getId()).get();
            UserAccount account = entreprise.getUserAccount();
            switch (registerRequest.getMode()) {
                case "profile":
                    account.setFirstName(registerRequest.getFirstName());
                    account.setLastName(registerRequest.getLastName());
                    account.setPhoneNumber(registerRequest.getPhoneNumber());
                    //account.setShow_profile(registerRequest.getShow_profile());
                    entreprise.setDescription(registerRequest.getDescription());
                    entreprise.setCompanySize(registerRequest.getCompanySize());
                    entreprise.setFoundedDate(LocalDate.parse(registerRequest.getFoundedDate()));
                    entreprise.setWebsite(registerRequest.getWebsite());
                    registerRequest.getCategoryJobs().stream().forEach(e->entreprise.addCategoryJobs(categoryJobRepository.findById(e).get()));

                    break;
                case "sociale":
                    entreprise.setFacebook(registerRequest.getFacebook());
                    entreprise.setTwitter(registerRequest.getTwitter());
                    entreprise.setLinkedin(registerRequest.getLinkedin());
                    entreprise.setGoogleplus(registerRequest.getGoogleplus());
                    break;
                case "contact":
                    entreprise.setCountry(registerRequest.getCountry());
                    account.setAddress(registerRequest.getAddress());
                    account.setLatitude(registerRequest.getLatitude());
                    account.setLongitude(registerRequest.getLongitude());
                    entreprise.setTown(registerRequest.getTown());
                    break;
                default:
                    if (!registerRequest.getImageUrl().isEmpty()) {
                        var f = account.getFileUrl();
                        if (f != null) {
                            fileUrlRepository.delete(f);
                        }
                        //var photo = fileService.convertImage(registerRequest.getImageUrl());
                        var fileurl = new FileUrl();
                        fileurl.setName(entreprise.getUserAccount().getFirstName());
                        fileurl.setUrl(registerRequest.getImageUrl());
                        fileurl.setDomain(domain + "images/");
                        account.setFileUrl(fileUrlRepository.save(fileurl));
                        account.setImageUrl(registerRequest.getImageUrl());
                    }
                    if (registerRequest.getCoverUrl()!=null) {
                        var f = account.getFileUrl();
                        if (f != null) {
                            fileUrlRepository.delete(f);
                        }
                       // var photo = fileService.convertImage(registerRequest.getImageUrl());
                        var fileurl = new FileUrl();
                        fileurl.setName(entreprise.getUserAccount().getFirstName());
                        fileurl.setUrl(registerRequest.getImageUrl());
                        fileurl.setDomain(domain + "images/");
                        entreprise.setCover_url(fileUrlRepository.save(fileurl));
                        entreprise.setCover_image_url(registerRequest.getImageUrl());
                    }
                    break;
            }
            userAccountRepository.save(account);
            return recruteurRepository.save(entreprise);
        } else {
            throw new BadRequestAlertException("Invalid user", "Entreprise", "idnull");
        }
    }

    @Override
    public Optional<RecruteurDDTO> partialUpdate(RecruteurDDTO recruteurDTO) {
        log.debug("Request to partially update Recruteur : {}", recruteurDTO);

        return recruteurRepository
            .findById(recruteurDTO.getId())
            .map(
                existingRecruteur -> {
                    recruteurMapper.partialUpdate(existingRecruteur, recruteurDTO);

                    return existingRecruteur;
                }
            )
            .map(recruteurRepository::save)
            .map(recruteurMapper::toDto);
    }

    @Override
    public List<RecruteurDDTO> findAll() {
        log.debug("Request to get all Recruteurs");
        return recruteurRepository.findAll().stream().map(recruteurMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
    public Page<Recruteur> findAllWithEagerRelationships(Pageable pageable) {
       // recruteurRepository.findAll().stream().forEach(System.out::println);
        return recruteurRepository.findAllWithEagerRelationships(pageable);
    }
    @Override
    public Optional<RecruteurDDTO> findOne(String id) {
        log.debug("Request to get Recruteur : {}", id);
        return recruteurRepository.findById(id).map(recruteurMapper::toDto);
    }

    @Override
    public Optional<Recruteur> findProfile(String id) {
        var account = userAccountRepository.findById(id);
        return recruteurRepository.findFirstByUserAccount(account.get());
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Recruteur : {}", id);
        recruteurRepository.deleteById(id);
    }

    @Override
    public Page<Recruteur> findSearch(Pageable pageable, String query, String city, String category, String founded,
            String compagny_size) {
                var items= recruteurRepository.findAll();
                if(!query.isEmpty()){
                 items= items.stream()
                         .filter(recruteur -> recruteur.getUserAccount().getFirstName() != null)
                         .filter(e->e.getUserAccount().getFirstName().toLowerCase().contains(query.toLowerCase()))
                         .collect(Collectors.toList());
                }
        if(!city.isEmpty()){
            items= items.stream()
                    .filter(recruteur -> recruteur.getUserAccount().getFirstName() != null)
                    .filter(recruteur -> recruteur.getCountry() != null)
                    .filter(e->e.getCountry().toLowerCase().contains(city.toLowerCase())||
                           e.getTown().toLowerCase().contains(city.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if(!founded.isEmpty()){
            items= items.stream()
                    .filter(recruteur -> recruteur.getUserAccount().getFirstName() != null)
                    .filter(recruteur -> recruteur.getFoundedDate() != null)
                    .filter(e->e.getFoundedDate().isEqual(LocalDate.parse(founded)))
                    .collect(Collectors.toList());
        }
        if(!compagny_size.isEmpty()){
            items= items.stream()
                    .filter(recruteur -> recruteur.getUserAccount().getFirstName() != null)
                    .filter(recruteur -> recruteur.getCompanySize() != null)
                    .filter(e->e.getCompanySize().toLowerCase().contains(compagny_size.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if(!category.isEmpty()){
            items= items.stream()
                    .filter(recruteur -> recruteur.getUserAccount().getFirstName() != null)
                    .filter(recruteur -> recruteur.getCategoryJobs() != null)
                    .filter(e->e.getCategoryJobs().contains(category.toLowerCase()))
                    .collect(Collectors.toList());
        }
                return new PageImpl<>(items,pageable, items.size());
    }
}
