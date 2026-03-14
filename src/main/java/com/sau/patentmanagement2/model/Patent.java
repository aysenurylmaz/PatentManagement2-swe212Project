package com.sau.patentmanagement2.model;

import com.sau.patentmanagement2.dtos.PatentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 32)
    private String title;

    @Column(length = 64)
    private String description;

    @OneToMany(mappedBy = "patent", cascade = CascadeType.ALL)
    private List<Certification> certifications;

    public Patent(PatentDTO patentDTO) {
        this.id = patentDTO.getId();
        this.title = patentDTO.getTitle();
        this.description = patentDTO.getDescription();
    }

    public PatentDTO viewAsPatentDTO() {
        return new PatentDTO(id, title, description);
    }
}
