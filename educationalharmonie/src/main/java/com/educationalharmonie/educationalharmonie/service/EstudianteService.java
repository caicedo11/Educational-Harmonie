package com.educationalharmonie.educationalharmonie.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.educationalharmonie.educationalharmonie.model.Estudiante;
import com.educationalharmonie.educationalharmonie.repository.EstudianteRepository;

@Service
public class EstudianteService {

    
    private EstudianteRepository estudianteRepository;

    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> findById(Long id) {
        return estudianteRepository.findById(id);
    }

    public void deleteById(Long id) {
        estudianteRepository.deleteById(id);
    }
}

