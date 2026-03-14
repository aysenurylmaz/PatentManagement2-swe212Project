package com.sau.patentmanagement2.service;

import com.sau.patentmanagement2.dtos.AuthorDTO;
import com.sau.patentmanagement2.model.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(Integer id);
    AuthorDTO createAuthor(Author author);
    AuthorDTO updateAuthor(Integer id, Author author);
    void deleteAuthor(Integer id);
}

