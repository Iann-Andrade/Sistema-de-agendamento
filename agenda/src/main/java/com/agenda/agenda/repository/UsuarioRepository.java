package com.agenda.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.agenda.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);
}