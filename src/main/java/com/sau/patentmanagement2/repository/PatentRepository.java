package com.sau.patentmanagement2.repository;

import com.sau.patentmanagement2.model.Patent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentRepository extends JpaRepository<Patent, Integer> {
}