package com.ubisam.exam10.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "exam_pencil")
public class Pencil {

    @Id
    @GeneratedValue
    private Long id;

    private String pencilName;
    private String pencilType;
    private String pencilColor;

    // @ManyToOne
    // private PencilCase pencilCase;

}
