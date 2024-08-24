package com.educationalharmonie.educationalharmonie.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.educationalharmonie.educationalharmonie.model.Acudiente;
import com.educationalharmonie.educationalharmonie.repository.AcudienteRepository;

@Service
public class AcudienteService {

    
    private AcudienteRepository acudienteRepository;

    public Acudiente save(Acudiente acudiente) {
        return acudienteRepository.save(acudiente);
    }

    public List<Acudiente> findAll() {
        return acudienteRepository.findAll();
    }

    public Optional<Acudiente> findById(Long id) {
        return acudienteRepository.findById(id);
    }

    public void deleteById(Long id) {
        acudienteRepository.deleteById(id);
    }
}

