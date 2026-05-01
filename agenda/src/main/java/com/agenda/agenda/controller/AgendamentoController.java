package com.agenda.agenda.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.agenda.agenda.model.Agendamento;
import com.agenda.agenda.service.AgendamentoService;


@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService service;

    @GetMapping
    public List<Agendamento> listar(){
        System.out.println("listarrr");
        return service.listar();
    }

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento){
        System.out.println("CRIAR FOI CHAMADO");
        return service.criar(agendamento);
    }

    @DeleteMapping("/{id}")
    public String remove (@PathVariable int id){
        return service.remover(id);
    }
   
}
