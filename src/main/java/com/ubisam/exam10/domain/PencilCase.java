package com.ubisam.exam10.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "exam_pencilCase")
public class PencilCase {

    @Id
    @GeneratedValue
    private Long id;

    private String pencilCaseName;
    private String pencilCaseLocation;

    // @OneToMany
    // private List<Pencil> pencils;

    @Transient
    private String searchName;

    @Transient
    private String searchLocation;

}
