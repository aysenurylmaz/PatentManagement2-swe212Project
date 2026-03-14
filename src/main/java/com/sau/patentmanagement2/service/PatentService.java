package com.sau.patentmanagement2.service;

import com.sau.patentmanagement2.dtos.PatentDTO;
import com.sau.patentmanagement2.model.Patent;
import java.util.List;

public interface PatentService {
    List<PatentDTO> getAllPatents();
    PatentDTO getPatentById(Integer id);
    PatentDTO createPatent(Patent patent);
    PatentDTO updatePatent(Integer id, Patent patent);
    void deletePatent(Integer id);
}