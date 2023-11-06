package com.okoho.okoho.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.okoho.okoho.domain.*;
import com.okoho.okoho.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.okoho.okoho.utils.Constant;
@Service
public class FackerService{

    @Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private CompetenceRepository competenceRepository;
	@Autowired
	private PersonnelRepository personnelRepository;
    @Autowired
	private RecruteurRepository recruteurRepository;
    @Autowired
	private CandidatRepository candidatRepository;
	@Autowired
	private UserAccountRepository userAccountRepository;
    @Autowired
	private OfferJobRepository offerJobRepository;
    @Autowired
	private CategoryJobRepository categoryJobRepository;
    @Autowired
	private RoleRepository roleRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private FileUrlRepository fileUrlRepository;
    Faker fakerWithLocales = new Faker(Locale.UK);
    public void generateFake(){
        userAccountRepository.deleteAll();
        candidatRepository.deleteAll();
        recruteurRepository.deleteAll();
        competenceRepository.deleteAll();
        offerJobRepository.deleteAll();
        categoryJobRepository.deleteAll();
        roleRepository.deleteAll();
        fileUrlRepository.deleteAll();
        blogRepository.deleteAll();
        var categoryJob=new CategoryJob();
        categoryJob.setTitle("Accounting / Finance");
        categoryJob.setDescription("Accounting / Finance");
        categoryJobRepository.save(categoryJob);
        var categoryJob2=new CategoryJob();
        categoryJob2.setTitle("Marketing");
        categoryJob2.setDescription("Marketing");
        categoryJobRepository.save(categoryJob2);
        var categoryJob3=new CategoryJob();
        categoryJob3.setTitle("Design");
        categoryJob3.setDescription("Design");
        categoryJobRepository.save(categoryJob3);
        var categoryJob4=new CategoryJob();
        categoryJob4.setTitle("Development");
        categoryJob4.setDescription("Development category");
        categoryJobRepository.save(categoryJob4);
        var role=new Role();
        role.setName(ERole.ROLE_CANDIDATE);
        roleRepository.save(role);
        var role2=new Role();
        role2.setName(ERole.ROLE_ENTREPRISE);
        roleRepository.save(role2);
        var role3=new Role();
        role3.setName(ERole.ROLE_ADMIN);
        roleRepository.save(role3);
        for (int i = 0; i < 40; i++) {
            Faker faker = new Faker();
            var blog=new Blog();
            blog.setTitle(faker.book().title());
            blog.setDescription(faker.shakespeare().hamletQuote());
            blog.setCreatedAt(LocalDate.now());
            var file=new FileUrl();
            file.setUrl(faker.avatar().image());
            file.setName(blog.getTitle());
            blog.setImage(fileUrlRepository.save(file));
            blogRepository.save(blog);
        }
        
/* 
        for (int i = 0; i < 120; i++) {
            Faker faker = new Faker();
            
            Competence competence=new Competence();
            competence.setLibelle(faker.job().title());
            competence.setDescription(faker.job().field());
            competenceRepository.save(competence); 
        }
        var lestLang=new String []{"en","fr","de"};
        var list=competenceRepository.findAll();
       for (int i = 0; i < 220; i++) {
        Faker faker = new Faker(); 
        UserAccount account=new UserAccount() ;
		Candidat candidat= new Candidat();
        account.firstName(faker.address().firstName());
        account.lastName(faker.address().lastName());
        account.codePhone(faker.address().countryCode());
        account.phoneNumber(faker.phoneNumber().cellPhone());
        account.setImageUrl(faker.avatar().image());
		account.setEmail(faker.bothify("????##@gmail.com"));
        account.setUserType(Constant.CANDIDATACCOUNT);
        account.setLangKey(Arrays.asList(lestLang).stream().findAny().get());
		account.setPassword(passwordEncoder.encode("123456789"));
        candidat.setCountry(faker.address().country());
        candidat.setDob(LocalDate.now());
        candidat.setCompetences(list.stream().skip(i).limit(8).collect(Collectors.toSet()));
		candidat.setUserAccount(account);
		userAccountRepository.save(account);
		candidatRepository.save(candidat); 
       }
       for (int i = 0; i < 120; i++) {
        Faker faker = new Faker(); 
        UserAccount account=new UserAccount() ;
		Recruteur recruteur= new Recruteur();
        account.firstName(faker.address().firstName());
        account.lastName(faker.address().lastName());
        account.codePhone(faker.address().countryCode());
        account.phoneNumber(faker.phoneNumber().cellPhone());
        account.setImageUrl(faker.company().logo());
		account.setEmail(faker.bothify("????##@gmail.com"));
        account.setUserType(Constant.ENTREPRISEACCOUNT);
        account.setLangKey(Arrays.asList(lestLang).stream().findAny().get());
		account.setPassword(passwordEncoder.encode("123456789"));
        recruteur.setCountry(faker.address().country());
        recruteur.setDob(LocalDate.now());
        recruteur.setBp(faker.address().buildingNumber());
        recruteur.setEntreprise(faker.company().name());
        recruteur.setDescription(faker.company().catchPhrase());
        recruteur.setCompanySize(faker.number().numberBetween(0, 9)+"-"+faker.number().numberBetween(11, 20));
        var date=faker.date().birthday(2, 20);
        recruteur.setFoundedDate(LocalDate.of(date.getYear(),6,2));
        recruteur.setLocation(faker.address().cityName());
        recruteur.setWebsite(faker.company().url());
        //recruteur.set(list.stream().skip(i).limit(8).collect(Collectors.toSet()));
        //userAccountRepository.save(account);
		recruteur.setUserAccount(userAccountRepository.save(account));
		//userAccountRepository.save(account);
		recruteurRepository.save(recruteur); 
       }
     var recruteurs=recruteurRepository.findAll();
     var cats=categoryJobRepository.findAll();
     var job_types=Arrays.asList("freelance","full time","intership","temporal","part time");
       for (int i = 0; i < 120; i++) {
        Faker faker = new Faker(); 
        OfferJob offerJob=new OfferJob();
        offerJob.competences(list.stream().skip(i).limit(8).collect(Collectors.toSet()));
        offerJob.setTitle(faker.job().title());
        offerJob.setCategoryJob(cats.stream().findAny().get());
        offerJob.setDescription(faker.lorem().paragraph());
        offerJob.setJobType(job_types.stream().findAny().get());
        offerJob.setRecruteur(recruteurs.stream().findAny().get());
        offerJob.setIsActive(faker.bool().bool());
        offerJob.setCreatedAt(LocalDate.now());
        offerJob.setMinSalary(String.valueOf(faker.number().randomDouble(2, 2000, 5000)));
        offerJob.setMax_salary(String.valueOf(faker.number().randomDouble(2, 5000, 12000)));
          offerJobRepository.save(offerJob);
    }
  */  } 
}
