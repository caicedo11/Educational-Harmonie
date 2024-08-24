package com.educationalharmonie.educationalharmonie.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.educationalharmonie.educationalharmonie.model.Reporte;
import com.educationalharmonie.educationalharmonie.repository.ReporteRepository;

@Service
public class ReporteService {

    
    private ReporteRepository reporteRepository;

    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }

    public List<Reporte> findByEstudianteId(Long estudianteId) {
        return reporteRepository.findByEstudianteId(estudianteId);
    }

    public Optional<Reporte> findById(Long id) {
        return reporteRepository.findById(id);
    }

    public void deleteById(Long id) {
        reporteRepository.deleteById(id);
    }
}
