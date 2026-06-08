package com.agenda.agenda.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.agenda.agenda.repository.AgendamentoRepository;
import com.agenda.agenda.model.Agendamento;
import com.agenda.agenda.service.AgendamentoService;


@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService service;
    @Autowired   
    private AgendamentoRepository repository;

    @GetMapping
    public List<Agendamento> listar(){
        System.out.println("listarrr");
        return service.listar();
    }

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento){
        System.out.println("CRIAR FOI CHAMADO");
        return service.criar(agendamento);
    };

    //testando função de lista de agendamentos
    @GetMapping("/horarios-disponiveis")
        public List<String> horariosDisponiveis(@RequestParam LocalDate data){

            System.out.println("DATA RECEBIDA: " + data);

            return service.buscarHorariosDisponiveis(data);
        };
    

    
}
