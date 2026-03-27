package com.ubisam.exam10.api.pencils;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ubisam.exam10.domain.Pencil;

public interface PencilRepository extends JpaRepository<Pencil, Long>, JpaSpecificationExecutor<Pencil> {

    List<Pencil> findByPencilName(String pencilName);

    List<Pencil> findByPencilType(String pencilType);

    List<Pencil> findByPencilColor(String pencilColor);

}
