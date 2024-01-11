package com.okoho.okoho;

import com.okoho.okoho.domain.ERole;
import com.okoho.okoho.repository.RoleRepository;
import com.okoho.okoho.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.okoho.okoho.domain.Competence;
import com.okoho.okoho.domain.Personnel;
import com.okoho.okoho.domain.UserAccount;
import com.okoho.okoho.repository.CompetenceRepository;
import com.okoho.okoho.repository.PersonnelRepository;
import com.okoho.okoho.repository.UserAccountRepository;

@SpringBootApplication
public class OkohoApplication implements CommandLineRunner{
    @Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private CompetenceRepository competenceRepository;
	@Autowired
	private PersonnelRepository personnelRepository;
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(OkohoApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
	
		Competence competence=new Competence();
		competence.setDescription("Test");
		competence.setLibelle("Manager");
		if(!userAccountRepository.findOneByEmail("superadmin@okoho.de").isPresent()){
			UserAccount account=new UserAccount() ;
		Personnel personnel= new Personnel();
		account.setEmail("superadmin@okoho.de");
		account.setPassword(passwordEncoder.encode("123456789"));
		personnel.setUserAccount(account);
		userAccountRepository.save(account);
		personnelRepository.save(personnel);	
		}
		//competenceRepository.save(competence);
		//competenceRepository.findAll().stream().forEach(e->System.out.println(e));
		//userAccountRepository.findAll().stream().forEach(System.out::println);
	}

}
