package com.sau.patentmanagement2.model;

import com.sau.patentmanagement2.dtos.AuthorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 16)
    private String name;

    @Column(length = 32)
    private String address;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Certification> certifications;


    public Author(AuthorDTO authorDTO) {
        this.id = authorDTO.getId();
        this.name = authorDTO.getName();
        this.address = authorDTO.getAddress();
    }

    // Entity'den DTO'ya dönüştüren metot
    public AuthorDTO viewAsAuthorDTO() {
        return new AuthorDTO(id, name, address);
    }
}

