package com.sau.patentmanagement2.controller;

import com.sau.patentmanagement2.dtos.PatentDTO;
import com.sau.patentmanagement2.model.Patent;
import com.sau.patentmanagement2.service.PatentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patent")
public class PatentController {
    private final static Logger logger = LoggerFactory.getLogger(PatentController.class);
    private final PatentService patentService;

    public PatentController(PatentService patentService) {
        this.patentService = patentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatentDTO>> getAllPatents() {
        return new ResponseEntity<>(patentService.getAllPatents(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<PatentDTO> getPatent(@PathVariable Integer id) {
        if (id == null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Get patent by id {}", id);
        return new ResponseEntity<>(patentService.getPatentById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PatentDTO> addPatent(@RequestBody Patent patent) {
        return new ResponseEntity<>(patentService.createPatent(patent), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatentDTO> updatePatent(@PathVariable Integer id, @RequestBody Patent patent) {
        if (id == null || id == 0 || patent == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(patentService.updatePatent(id, patent), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Patent> deletePatent(@PathVariable Integer id) {
        if (id == null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        patentService.deletePatent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}