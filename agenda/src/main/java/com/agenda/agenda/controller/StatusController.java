package com.agenda.agenda.controller;

import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agenda.model.Agendamento;
import com.agenda.agenda.model.StatusAgendamentos;
import com.agenda.agenda.service.AgendamentoService;

@Controller
@RestController
@RequestMapping("/agendamentos")
public class StatusController {
    
    @Autowired
    private AgendamentoService service;

    @PostMapping("/confirmar/{id}")
    public void confirmar(@PathVariable int id){
        System.out.println("CONFIRMAR FOI CHAMADO");
        service.confirmar(id);
    };


}
