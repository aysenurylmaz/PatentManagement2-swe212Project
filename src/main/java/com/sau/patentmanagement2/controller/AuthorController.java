package com.sau.patentmanagement2.controller;

import com.sau.patentmanagement2.dtos.AuthorDTO;
import com.sau.patentmanagement2.model.Author;
import com.sau.patentmanagement2.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Integer id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Get author by id {}", id);
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody Author author) {
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
        if ((id == null || id == 0 || author == null) || (!id.equals(author.getId()))) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(authorService.updateAuthor(id, author), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}