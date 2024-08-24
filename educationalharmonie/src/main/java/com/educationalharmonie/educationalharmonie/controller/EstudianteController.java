package com.educationalharmonie.educationalharmonie.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.educationalharmonie.educationalharmonie.model.Estudiante;
import com.educationalharmonie.educationalharmonie.model.Reporte;
import com.educationalharmonie.educationalharmonie.service.EstudianteService;
import com.educationalharmonie.educationalharmonie.service.ReporteService;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final ReporteService reporteService;

    
    public EstudianteController(EstudianteService estudianteService, ReporteService reporteService) {
        this.estudianteService = estudianteService;
        this.reporteService = reporteService;
    }

    @GetMapping("/{id}")
    public String verEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.findById(id).orElse(null);
        List<Reporte> reportes = reporteService.findByEstudianteId(id);
        model.addAttribute("estudiantes", estudiante);
        model.addAttribute("reportes", reportes);
        return "estudiante";
    }

    @GetMapping("/historial")
    public String verHistorial(Model model, Principal principal) {
        Estudiante estudiante = estudianteService.findAll().stream()
                .filter(e -> e.getUsuario().getNombreUsuario().equals(principal.getName()))
                .findFirst().orElse(null);
        if (estudiante != null) {
            List<Reporte> reportes = reporteService.findByEstudianteId(estudiante.getId());
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("reportes", reportes);
        }
        return "historial_estudiante";
    }
}
