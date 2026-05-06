package com.agenda.agenda.controller;

import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agenda.model.Agendamento;
import com.agenda.agenda.model.StatusAgendamentos;
import com.agenda.agenda.repository.AgendamentoRepository;
import com.agenda.agenda.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class StatusController {
    
    @Autowired
    private AgendamentoService service;
    private AgendamentoRepository repository;

    @PutMapping("/confirmar/{id}")
    public void confirmar(@PathVariable int id){
        System.out.println("CONFIRMAR FOI CHAMADO");
        service.confirmar(id);
    };


    //chama o service com a função de cancelar o agendamento 
    /*@DeleteMapping("/{id}")
    public ResponseEntity<String> cancelar(Integer id){
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body("Agendamento não encontrado");
        }

        repository.deleteById(id);
        return ResponseEntity.ok("Horário cancelado com sucesso!");*/

        @DeleteMapping("/{id}")
        public void cancelar(@PathVariable Integer id){
            System.out.println("Cancelar FOI CHAMADO");
            service.cancelar(id);
        };

    };

