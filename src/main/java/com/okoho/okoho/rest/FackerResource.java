package com.okoho.okoho.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.rest.errors.BadRequestAlertException;
import com.okoho.okoho.service.FackerService;
import com.okoho.okoho.service.dto.RegisterRequest;
import com.okoho.okoho.utils.HeaderUtil;

@RestController
@RequestMapping("/v1")
public class FackerResource {
    private final FackerService fackerService;

    public FackerResource(FackerService fackerService) {
        this.fackerService = fackerService;
    }
    @PostMapping("/fackers")
    public ResponseEntity<Candidat> createFacker(@RequestBody RegisterRequest candidat) throws URISyntaxException, BadRequestAlertException {
    
         fackerService.generateFake();
        return ResponseEntity
            .created(new URI("/api/fackers/" ))
            .body(null);
    }
}
