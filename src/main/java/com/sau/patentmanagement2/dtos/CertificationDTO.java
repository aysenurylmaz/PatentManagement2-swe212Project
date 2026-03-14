package com.sau.patentmanagement2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDTO {
    private Integer id;
    private LocalDate issueDate;
    private Integer durationInYear;
    private AuthorDTO authorDTO;
    private PatentDTO patentDTO;

    public CertificationDTO(Integer id, LocalDate issueDate, Integer durationInYear) {
        this.id = id;
        this.issueDate = issueDate;
        this.durationInYear = durationInYear;
    }
}
