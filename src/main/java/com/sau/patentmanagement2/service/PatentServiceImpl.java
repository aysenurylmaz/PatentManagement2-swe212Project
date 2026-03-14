package com.sau.patentmanagement2.service;

import com.sau.patentmanagement2.dtos.PatentDTO;
import com.sau.patentmanagement2.exception.ErrorMessages;
import com.sau.patentmanagement2.exception.ResourceAlreadyExistsException;
import com.sau.patentmanagement2.exception.ResourceNotFoundException;
import com.sau.patentmanagement2.model.Patent;
import com.sau.patentmanagement2.repository.PatentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatentServiceImpl implements PatentService {
    private final PatentRepository patentRepository;

    public PatentServiceImpl(PatentRepository patentRepository) {
        this.patentRepository = patentRepository;
    }

    public PatentDTO getPatentById(Integer id) {
        return patentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_PATENT_NOT_FOUND + ": " + id)).viewAsPatentDTO();
    }

    public List<PatentDTO> getAllPatents() {
        return patentRepository.findAll().stream().map(Patent::viewAsPatentDTO).toList();
    }

    public PatentDTO createPatent(Patent patent) {
        // ID null kontrolünü ekledim ki eğer Swagger'dan ID gönderilmezse sistem çökmesin
        if (patent.getId() != null && patentRepository.findById(patent.getId()).isPresent()) {
            throw new ResourceAlreadyExistsException(ErrorMessages.ERROR_PATENT_ALREADY_EXIST + ": " + patent.getId());
        }

        Patent savedPatent = patentRepository.save(patent);
        System.out.println("LOG INFO: New Patent Added - ID: " + savedPatent.getId());

        return savedPatent.viewAsPatentDTO();
    }

    public PatentDTO updatePatent(Integer id, Patent patent) {
        patentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_PATENT_NOT_FOUND + ": " + id));

        patent.setId(id);
        Patent updatedPatent = patentRepository.save(patent);
        System.out.println("LOG INFO: Patent Updated - ID: " + updatedPatent.getId());

        return updatedPatent.viewAsPatentDTO();
    }

    public void deletePatent(Integer id) {
        patentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_PATENT_NOT_FOUND + ": " + id));

        patentRepository.deleteById(id);
        System.out.println("LOG INFO: Patent Deleted - ID: " + id);
    }
}