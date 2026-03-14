package com.sau.patentmanagement2.service;

import com.sau.patentmanagement2.dtos.AuthorDTO;
import com.sau.patentmanagement2.dtos.CertificationDTO;
import com.sau.patentmanagement2.dtos.PatentDTO;
import com.sau.patentmanagement2.exception.ErrorMessages;
import com.sau.patentmanagement2.exception.ResourceAlreadyExistsException;
import com.sau.patentmanagement2.exception.ResourceNotFoundException;
import com.sau.patentmanagement2.model.Certification;
import com.sau.patentmanagement2.repository.AuthorRepository;
import com.sau.patentmanagement2.repository.CertificationRepository;
import com.sau.patentmanagement2.repository.PatentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationServiceImpl implements CertificationService {

    // Hocanın yaptığı gibi 3 Repository'yi de buraya dahil ediyoruz
    private final CertificationRepository certificationRepository;
    private final AuthorRepository authorRepository;
    private final PatentRepository patentRepository;

    public CertificationServiceImpl(CertificationRepository certificationRepository, AuthorRepository authorRepository, PatentRepository patentRepository) {
        this.certificationRepository = certificationRepository;
        this.authorRepository = authorRepository;
        this.patentRepository = patentRepository;
    }

    @Override
    public CertificationDTO getCertificationById(Integer id) {
        Certification c = certificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_CERTIFICATION_NOT_FOUND + ": " + id));

        // Ana DTO
        CertificationDTO certificationDTO = new CertificationDTO(c.getId(), c.getIssueDate(), c.getDurationInYear());

        // Yazar DTO'sunu oluşturup içine atıyoruz
        if (c.getAuthor() != null) {
            AuthorDTO authorDTO = new AuthorDTO(c.getAuthor().getId(), c.getAuthor().getName(), c.getAuthor().getAddress());
            certificationDTO.setAuthorDTO(authorDTO);
        }

        // Patent DTO'sunu oluşturup içine atıyoruz
        if (c.getPatent() != null) {
            PatentDTO patentDTO = new PatentDTO(c.getPatent().getId(), c.getPatent().getTitle(), c.getPatent().getDescription());
            certificationDTO.setPatentDTO(patentDTO);
        }

        return certificationDTO;
    }

    @Override
    public List<CertificationDTO> getAllCertifications() {
        return certificationRepository.findAll().stream().map(Certification::viewAsCertificationDTO).toList();
    }

    @Override
    public CertificationDTO createCertification(Certification certification) {
        if (certification.getId() != null && certificationRepository.findById(certification.getId()).isPresent()) {
            throw new ResourceAlreadyExistsException(ErrorMessages.ERROR_CERTIFICATION_ALREADY_EXIST + ": " + certification.getId());
        }

        Certification savedCert = certificationRepository.save(certification);
        // Log yazdırma
        System.out.println("LOG INFO: New Certificate Added - ID: " + savedCert.getId());

        return savedCert.viewAsCertificationDTO();
    }

    @Override
    public CertificationDTO updateCertification(Integer id, Certification certification) {
        certificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_CERTIFICATION_NOT_FOUND + ": " + id));

        certification.setId(id);
        Certification updatedCert = certificationRepository.save(certification);

        // Log yazdırma
        System.out.println("LOG INFO: Certificate Updated - ID: " + updatedCert.getId());

        return updatedCert.viewAsCertificationDTO();
    }

    @Override
    public void deleteCertification(Integer id) {
        certificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_CERTIFICATION_NOT_FOUND + ": " + id));

        certificationRepository.deleteById(id);
        System.out.println("LOG INFO: Certificate Deleted - ID: " + id);
    }
}