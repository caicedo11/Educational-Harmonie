package com.educationalharmonie.educationalharmonie.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.educationalharmonie.educationalharmonie.model.Grupo;
import com.educationalharmonie.educationalharmonie.repository.GrupoRepository;

@Service
public class GrupoService {

    
    private GrupoRepository grupoRepository;

    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public List<Grupo> findAll() {
        return grupoRepository.findAll();
    }

    public Optional<Grupo> findById(Long id) {
        return grupoRepository.findById(id);
    }

    public void deleteById(Long id) {
        grupoRepository.deleteById(id);
    }
}

