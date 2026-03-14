package com.sau.patentmanagement2.controller;

import com.sau.patentmanagement2.dtos.CertificationDTO;
import com.sau.patentmanagement2.model.Certification;
import com.sau.patentmanagement2.service.CertificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certification")
public class CertificationController {
    private final static Logger logger = LoggerFactory.getLogger(CertificationController.class);
    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CertificationDTO>> getAllCertifications() {
        return new ResponseEntity<>(certificationService.getAllCertifications(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<CertificationDTO> getCertification(@PathVariable Integer id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Get certification by id {}", id);
        return new ResponseEntity<>(certificationService.getCertificationById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CertificationDTO> addCertification(@RequestBody Certification certification) {
        if (certification == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(certificationService.createCertification(certification), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CertificationDTO> updateCertification(@PathVariable Integer id, @RequestBody Certification certification) {
        if ((id == null || id == 0 || certification == null) || (!id.equals(certification.getId()))) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(certificationService.updateCertification(id, certification), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Certification> deleteCertification(@PathVariable Integer id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        certificationService.deleteCertification(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}