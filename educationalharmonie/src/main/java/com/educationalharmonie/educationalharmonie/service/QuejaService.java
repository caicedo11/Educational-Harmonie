package com.educationalharmonie.educationalharmonie.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.educationalharmonie.educationalharmonie.model.Queja;
import com.educationalharmonie.educationalharmonie.repository.QuejaRepository;

@Service
public class QuejaService {

    
    private QuejaRepository quejaRepository;

    public Queja save(Queja queja) {
        return quejaRepository.save(queja);
    }

    public List<Queja> findAll() {
        return quejaRepository.findAll();
    }

    public List<Queja> findByEstudianteId(Long estudianteId) {
        return quejaRepository.findByEstudianteId(estudianteId);
    }

    public Optional<Queja> findById(Long id) {
        return quejaRepository.findById(id);
    }

    public void deleteById(Long id) {
        quejaRepository.deleteById(id);
    }
}
