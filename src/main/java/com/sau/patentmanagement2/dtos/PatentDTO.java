package com.sau.patentmanagement2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatentDTO {
    private Integer id;
    private String title;
    private String description;

}
