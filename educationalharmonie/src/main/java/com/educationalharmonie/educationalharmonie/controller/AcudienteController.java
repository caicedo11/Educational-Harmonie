package com.educationalharmonie.educationalharmonie.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.educationalharmonie.educationalharmonie.model.Acudiente;
import com.educationalharmonie.educationalharmonie.model.Estudiante;
import com.educationalharmonie.educationalharmonie.model.Queja;
import com.educationalharmonie.educationalharmonie.service.AcudienteService;
import com.educationalharmonie.educationalharmonie.service.EstudianteService;
import com.educationalharmonie.educationalharmonie.service.QuejaService;

@Controller
@RequestMapping("/acudientes")
public class AcudienteController {

    private final AcudienteService acudienteService;
    private final EstudianteService estudianteService;
    private final QuejaService quejaService;

    public AcudienteController(AcudienteService acudienteService, EstudianteService estudianteService, QuejaService quejaService) {
        this.acudienteService = acudienteService;
        this.estudianteService = estudianteService;
        this.quejaService = quejaService;
    }

    @GetMapping("/estudiantes")
    public String listarEstudiantes(Model model, Principal principal) {
        Acudiente acudiente = acudienteService.findAll().stream()
                .filter(a -> a.getUsuario().getNombreUsuario().equals(principal.getName()))
                .findFirst().orElse(null);
        if (acudiente != null) {
            List<Estudiante> estudiantes = estudianteService.findAll();
            model.addAttribute("estudiantes", estudiantes);
        }
        return "estudiantes_acudiente";
    }

    @GetMapping("/quejar/{estudianteId}")
    public String mostrarFormularioQueja(@PathVariable Long estudianteId, Model model) {
        model.addAttribute("queja", new Queja());
        model.addAttribute("estudianteId", estudianteId);
        return "queja";
    }

    @PostMapping("/quejar")
    public String registrarQueja(@ModelAttribute Queja queja, @RequestParam Long estudianteId, Principal principal) {
        Acudiente acudiente = acudienteService.findAll().stream()
                .filter(a -> a.getUsuario().getNombreUsuario().equals(principal.getName()))
                .findFirst().orElse(null);
        queja.setAcudiente(acudiente);
        quejaService.save(queja);
        return "redirect:/acudientes/estudiantes";
    }

    @GetMapping("/historial/{estudianteId}")
    public String verHistorialEstudiante(@PathVariable Long estudianteId, Model model) {
        List<Queja> quejas = quejaService.findByEstudianteId(estudianteId);
        model.addAttribute("quejas", quejas);
        return "historial_acudiente";
    }
}
