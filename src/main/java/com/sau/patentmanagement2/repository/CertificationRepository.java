package com.sau.patentmanagement2.repository;

import com.sau.patentmanagement2.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Integer> {
}