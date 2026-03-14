package com.sau.patentmanagement2.repository;

import com.sau.patentmanagement2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}