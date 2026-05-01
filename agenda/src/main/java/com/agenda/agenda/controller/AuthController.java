package com.agenda.agenda.controller;


import com.agenda.agenda.model.Usuario;
import com.agenda.agenda.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")

public class AuthController {
    @Autowired
    private UsuarioRepository repo;
    
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario user) {
    
        String email = user.getEmail();
        String senha = user.getSenha();

        Usuario usuario = repo.findByEmail(user.getEmail());

        if(email.equals("admin") && senha.equals ("123")) {
            return Map.of("token", "abc123");
        }
        throw new RuntimeException("Login inválido");
    }
}