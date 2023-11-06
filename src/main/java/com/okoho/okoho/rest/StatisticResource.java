package com.okoho.okoho.rest;

import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.service.StatisticService;
import com.okoho.okoho.service.dto.StatisticResponse;
import com.okoho.okoho.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/v1")
@CrossOrigin
public class StatisticResource {
    @Autowired
    public StatisticService statisticService;
    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponse> getStatistics() {
        Optional<StatisticResponse> statisticResponse = statisticService.getStatistics();
        return ResponseUtil.wrapOrNotFound(statisticResponse);
    }
    @GetMapping("/statistics/candidat/{id}")
    public ResponseEntity<StatisticResponse> getStatisticsCandidat(@PathVariable String id) {
        Optional<StatisticResponse> statisticResponse = statisticService.getDashbord(id);
        return ResponseUtil.wrapOrNotFound(statisticResponse);
    }
    @GetMapping("/statistics/entreprise/{id}")
    public ResponseEntity<StatisticResponse> getStatisticEntreprise(@PathVariable String id) {
        Optional<StatisticResponse> statisticResponse = statisticService.getDashbordEntreprise(id);
        return ResponseUtil.wrapOrNotFound(statisticResponse);
    }
}
