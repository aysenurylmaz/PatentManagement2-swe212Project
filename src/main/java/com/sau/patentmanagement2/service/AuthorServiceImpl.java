package com.sau.patentmanagement2.service;

import com.sau.patentmanagement2.dtos.AuthorDTO;
import com.sau.patentmanagement2.exception.ErrorMessages;
import com.sau.patentmanagement2.exception.ResourceAlreadyExistsException;
import com.sau.patentmanagement2.exception.ResourceNotFoundException;
import com.sau.patentmanagement2.model.Author;
import com.sau.patentmanagement2.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDTO getAuthorById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_AUTHOR_NOT_FOUND + ": " + id)).viewAsAuthorDTO();
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(Author::viewAsAuthorDTO).toList();
    }

    public AuthorDTO createAuthor(Author author) {
        // ID null değilse ve veritabanında varsa hata fırlat
        if (author.getId() != null && authorRepository.findById(author.getId()).isPresent()) {
            throw new ResourceAlreadyExistsException(ErrorMessages.ERROR_AUTHOR_ALREADY_EXIST + ": " + author.getId());
        }

        Author savedAuthor = authorRepository.save(author);
        //Yeni veri eklemesi için log
        System.out.println("LOG INFO: New Author Added - ID: " + savedAuthor.getId());

        return savedAuthor.viewAsAuthorDTO();
    }

    public AuthorDTO updateAuthor(Integer id, Author author) {
        authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_AUTHOR_NOT_FOUND + ": " + id));
        author.setId(id);

        Author updatedAuthor = authorRepository.save(author);
        //Veri güncellemesi için log
        System.out.println("LOG INFO: Author Updated - ID: " + updatedAuthor.getId());

        return updatedAuthor.viewAsAuthorDTO();
    }

    public void deleteAuthor(Integer id) {
        authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_AUTHOR_NOT_FOUND + ": " + id));
        authorRepository.deleteById(id);
        System.out.println("LOG INFO: Author Deleted - ID: " + id);
    }
}
