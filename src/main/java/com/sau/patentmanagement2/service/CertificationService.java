package com.sau.patentmanagement2.service;

import com.sau.patentmanagement2.dtos.CertificationDTO;
import com.sau.patentmanagement2.model.Certification;
import java.util.List;

public interface CertificationService {
    List<CertificationDTO> getAllCertifications();
    CertificationDTO getCertificationById(Integer id);
    CertificationDTO createCertification(Certification certification);
    CertificationDTO updateCertification(Integer id, Certification certification);
    void deleteCertification(Integer id);
}