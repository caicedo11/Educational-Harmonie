package com.educationalharmonie.educationalharmonie.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educationalharmonie.educationalharmonie.model.Queja;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
    List<Queja> findByEstudianteId(Long estudianteId);
}
