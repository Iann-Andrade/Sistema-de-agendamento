package com.agenda.agenda.service;

import java.util.ArrayList;
import java.util.List;

import com.agenda.agenda.model.Agendamento;
import com.agenda.agenda.model.StatusAgendamentos;
import com.agenda.agenda.repository.AgendamentoRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    
    @Autowired
    private AgendamentoRepository repository;

     public List<Agendamento> listar() {
         return repository.findAll();
     }

     public Agendamento criar(Agendamento agendamento){

        List<Agendamento> lista = repository.findAll();
        System.out.println("CHEGOU NO SERVICE");

            for(Agendamento a : lista){
                if (a.getData().equals(agendamento.getData()) &&
                a.getHora().equals(agendamento.getHora())) 
                {
                    throw new RuntimeException("Horário ocupado!");
                }
            }
            
        agendamento.setStatusDescricao(StatusAgendamentos.PENDENTE);
        System.out.println(agendamento.getStatusDescricao());

        return repository.save(agendamento);
    }
    

     public String confirmar(Integer id){
        List<Agendamento> lista = repository.findAll();

        System.out.println("Chamou o confirmar");
        for(Agendamento agendamento : lista){
            if(agendamento.getId() == id){
                agendamento.setStatusDescricao(StatusAgendamentos.CONFIRMADO);
                repository.save(agendamento);
                return "Precença confirmada!";
                }
            }
                    return null;            
    }

    public boolean verificar(Integer id){
        return repository.existsById(id);
    }

    public String cancelar(Integer id) {
        
              if (!repository.existsById(id)) {
                throw new RuntimeException("Agendamento não encontrado");
              }
        
              repository.deleteById(id);
              return "Horário cancelado!";
    }

}
