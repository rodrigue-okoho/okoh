package com.okoho.okoho.service.impl;

import com.okoho.okoho.domain.CategoryJob;
import com.okoho.okoho.repository.CandidatRepository;
import com.okoho.okoho.repository.OfferJobRepository;
import com.okoho.okoho.repository.RecruteurRepository;
import com.okoho.okoho.repository.ApplicantJobRepository;
import com.okoho.okoho.service.MailService;
import com.okoho.okoho.service.StatisticService;
import com.okoho.okoho.service.dto.MessageDTO;
import com.okoho.okoho.service.dto.StatisticResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    public OfferJobRepository offerJobRepository;
    @Autowired
    public CandidatRepository candidatRepository;
    @Autowired
    public RecruteurRepository recruteurRepository;
    @Autowired
    public ApplicantJobRepository applicantJobRepository;
    @Autowired
    public MailService mailService;
    @Value("${mail.from}")
    private String addressContact;
    @Override
    public Optional<StatisticResponse> getStatistics() {
        var st=new StatisticResponse();
        st.setAllCandidats(((int) candidatRepository.count()));
        st.setAllJobs(((int) offerJobRepository.count()));
        st.setAllEmployers(((int) recruteurRepository.count()));
        return Optional.of(st);
    }

    @Override
    public void sendCantactus(MessageDTO messageDTO) {
        mailService.sendEmail(addressContact, messageDTO.getSubject(), messageDTO.getMessage(), false,false);
    }

    @Override
    public Optional<StatisticResponse> getDashbord(String id) {
        var candidat=candidatRepository.findById(id).get();
        var jobalerts= offerJobRepository.findAll()
        .stream()
        .filter(offerJob -> {
            var val=false;
            for (CategoryJob cat: candidat.getCategoryJobs()) {
                if (offerJob.getCategoryJobs().stream().map(CategoryJob::getId).collect(Collectors.toList()).contains(cat.getId())) {
                
                    val = true;
                    break;
                }
            }
            return val;
        })
        .collect(Collectors.toList());
        
        var offers=applicantJobRepository.findAllWithEagerRelationships().stream()
        .filter(applicantJob->applicantJob.getCandidat() !=null)
                .filter(applicantJob -> applicantJob.getCandidat().getId().equals(id))
                .collect(Collectors.toList());
        var st=new StatisticResponse();
        st.setJobAlert(((int) jobalerts.size()));
        st.setApplication(((int) offers.size()));
        st.setMessage(((int) recruteurRepository.count()));
        st.setShortList(((int) recruteurRepository.count()));
        return Optional.of(st);
    }

    @Override
    public Optional<StatisticResponse> getDashbordEntreprise(String id) {
        var entreprise=recruteurRepository.findById(id).get();


        var offers=applicantJobRepository.findAllWithEagerRelationships().stream()
        .filter(applicantJob->applicantJob.getOfferJob() !=null)
                .filter(applicantJob -> applicantJob.getOfferJob().getRecruteur().getId().equals(id))
                .collect(Collectors.toList());

        var postedJob=offerJobRepository.findAll().stream()
        .filter(e->e.getRecruteur().getId().equals(id)).collect(Collectors.toList());
        var st=new StatisticResponse();
        st.setJobPosted(((int) postedJob.size()));
        st.setApplication(((int) offers.size()));
        st.setMessage(((int) recruteurRepository.count()));
        st.setShortList(((int) recruteurRepository.count()));
        return Optional.of(st);
    }
}
