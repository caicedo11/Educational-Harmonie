package com.educationalharmonie.educationalharmonie.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.educationalharmonie.educationalharmonie.model.Usuario;
import com.educationalharmonie.educationalharmonie.service.UsuarioService;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;


    public RegistroController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioService.save(usuario);
        return "redirect:/login";
    }
}
