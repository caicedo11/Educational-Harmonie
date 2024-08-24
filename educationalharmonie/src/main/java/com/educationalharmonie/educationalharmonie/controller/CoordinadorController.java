package com.educationalharmonie.educationalharmonie.controller;

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
import com.educationalharmonie.educationalharmonie.model.Grupo;
import com.educationalharmonie.educationalharmonie.model.Usuario;
import com.educationalharmonie.educationalharmonie.service.AcudienteService;
import com.educationalharmonie.educationalharmonie.service.EstudianteService;
import com.educationalharmonie.educationalharmonie.service.GrupoService;
import com.educationalharmonie.educationalharmonie.service.UsuarioService;

@Controller
@RequestMapping("/coordinadores")
public class CoordinadorController {

    private final UsuarioService usuarioService;
    private final GrupoService grupoService;
    private final EstudianteService estudianteService;
    private final AcudienteService acudienteService;

    
    public CoordinadorController(UsuarioService usuarioService, GrupoService grupoService, EstudianteService estudianteService, AcudienteService acudienteService) {
        this.usuarioService = usuarioService;
        this.grupoService = grupoService;
        this.estudianteService = estudianteService;
        this.acudienteService = acudienteService;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios_coordinador";
    }

    @GetMapping("/grupos")
    public String listarGrupos(Model model) {
        List<Grupo> grupos = grupoService.findAll();
        model.addAttribute("grupos", grupos);
        return "grupos_coordinador";
    }

    @GetMapping("/crearGrupo")
    public String mostrarFormularioCrearGrupo(Model model) {
        model.addAttribute("grupo", new Grupo());
        return "crear_grupo";
    }

    @PostMapping("/crearGrupo")
    public String crearGrupo(@ModelAttribute Grupo grupo) {
        grupoService.save(grupo);
        return "redirect:/coordinadores/grupos";
    }

    @GetMapping("/grupo/{id}")
    public String verGrupo(@PathVariable Long id, Model model) {
        Grupo grupo = grupoService.findById(id).orElse(null);
        model.addAttribute("grupo", grupo);
        return "grupo_coordinador";
    }

    @GetMapping("/agregarEstudiante/{grupoId}")
    public String mostrarFormularioAgregarEstudiante(@PathVariable Long grupoId, Model model) {
        model.addAttribute("estudiantes", estudianteService.findAll());
        model.addAttribute("grupoId", grupoId);
        return "agregar_estudiante";
    }

    @PostMapping("/agregarEstudiante")
    public String agregarEstudiante(@RequestParam Long estudianteId, @RequestParam Long grupoId) {
        Estudiante estudiante = estudianteService.findById(estudianteId).orElse(null);
        Grupo grupo = grupoService.findById(grupoId).orElse(null);
        if (estudiante != null && grupo != null) {
            estudiante.setGrupo(grupo);
            estudianteService.save(estudiante);
        }
        return "redirect:/coordinadores/grupo/" + grupoId;
    }

    @GetMapping("/asignarDocente/{grupoId}")
    public String mostrarFormularioAsignarDocente(@PathVariable Long grupoId, Model model) {
        model.addAttribute("docentes", usuarioService.findAll());
        model.addAttribute("grupoId", grupoId);
        return "asignar_docente";
    }

    @PostMapping("/asignarDocente")
    public String asignarDocente(@RequestParam Long docenteId, @RequestParam Long grupoId) {
        Usuario docente = usuarioService.findById(docenteId).orElse(null);
        Grupo grupo = grupoService.findById(grupoId).orElse(null);
        if (docente != null && grupo != null) {
            grupo.setDocente(docente);
            grupoService.save(grupo);
        }
        return "redirect:/coordinadores/grupo/" + grupoId;
    }

    @GetMapping("/asignarAcudiente/{estudianteId}")
    public String mostrarFormularioAsignarAcudiente(@PathVariable Long estudianteId, Model model) {
        model.addAttribute("acudientes", acudienteService.findAll());
        model.addAttribute("estudianteId", estudianteId);
        return "asignar_acudiente";
    }

    @PostMapping("/asignarAcudiente")
    public String asignarAcudiente(@RequestParam Long acudienteId, @RequestParam Long estudianteId) {
        Estudiante estudiante = estudianteService.findById(estudianteId).orElse(null);
        Acudiente acudiente = acudienteService.findById(acudienteId).orElse(null);
        if (estudiante != null && acudiente != null) {
            estudiante.setAcudiente(acudiente);
            estudianteService.save(estudiante);
        }
        return "redirect:/coordinadores/estudiantes";
    }
}


