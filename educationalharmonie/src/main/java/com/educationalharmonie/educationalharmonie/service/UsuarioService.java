package com.educationalharmonie.educationalharmonie.service;


import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.educationalharmonie.educationalharmonie.model.Usuario;
import com.educationalharmonie.educationalharmonie.repository.UsuarioRepository;

@Service
public class UsuarioService {

    
    private UsuarioRepository usuarioRepository;


    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
