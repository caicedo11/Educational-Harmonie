package com.educationalharmonie.educationalharmonie.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.educationalharmonie.educationalharmonie.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}

