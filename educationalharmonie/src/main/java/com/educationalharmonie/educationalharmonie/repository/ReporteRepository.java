package com.educationalharmonie.educationalharmonie.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educationalharmonie.educationalharmonie.model.Reporte;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByEstudianteId(Long estudianteId);
}

