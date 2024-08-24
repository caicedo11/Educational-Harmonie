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

import com.educationalharmonie.educationalharmonie.model.Grupo;
import com.educationalharmonie.educationalharmonie.model.Reporte;
import com.educationalharmonie.educationalharmonie.model.Usuario;
import com.educationalharmonie.educationalharmonie.service.GrupoService;
import com.educationalharmonie.educationalharmonie.service.ReporteService;
import com.educationalharmonie.educationalharmonie.service.UsuarioService;

@Controller
@RequestMapping("/docentes")
public class DocenteController {

    private final UsuarioService usuarioService;
    private final GrupoService grupoService;
    private final ReporteService reporteService;

    
    public DocenteController(UsuarioService usuarioService, GrupoService grupoService, ReporteService reporteService) {
        this.usuarioService = usuarioService;
        this.grupoService = grupoService;
        this.reporteService = reporteService;
    }

    @GetMapping("/grupos")
    public String listarGrupos(Model model, Principal principal) {
        Usuario docente = usuarioService.findByNombreUsuario(principal.getName()).orElse(null);
        if (docente != null) {
            List<Grupo> grupos = grupoService.findAll();
            model.addAttribute("grupos", grupos);
        }
        return "grupos_docente";
    }

    @GetMapping("/grupo/{id}")
    public String verGrupo(@PathVariable Long id, Model model) {
        Grupo grupo = grupoService.findById(id).orElse(null);
        model.addAttribute("grupo", grupo);
        return "grupo_docente";
    }

    @GetMapping("/reportar/{estudianteId}")
    public String mostrarFormularioReporte(@PathVariable Long estudianteId, Model model) {
        model.addAttribute("reporte", new Reporte());
        model.addAttribute("estudianteId", estudianteId);
        return "reporte";
    }

    @PostMapping("/reportar")
    public String registrarReporte(@ModelAttribute Reporte reporte, @RequestParam Long estudianteId, Principal principal) {
        Usuario docente = usuarioService.findByNombreUsuario(principal.getName()).orElse(null);
        reporte.setDocente(docente);
        reporteService.save(reporte);
        return "redirect:/docentes/grupos";
    }

    @GetMapping("/historial/{estudianteId}")
    public String verHistorialEstudiante(@PathVariable Long estudianteId, Model model) {
        List<Reporte> reportes = reporteService.findByEstudianteId(estudianteId);
        model.addAttribute("reportes", reportes);
        return "historial_docente";
    }
}
