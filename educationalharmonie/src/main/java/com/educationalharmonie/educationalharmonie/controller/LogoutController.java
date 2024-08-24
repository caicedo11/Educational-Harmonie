package com.educationalharmonie.educationalharmonie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // Invalida la sesión actual
        return "redirect:/"; // Redirecciona a la página de inicio despues de cerrar sesión
    }
}
