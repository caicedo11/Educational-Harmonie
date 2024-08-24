package com.educationalharmonie.educationalharmonie.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educationalharmonie.educationalharmonie.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
