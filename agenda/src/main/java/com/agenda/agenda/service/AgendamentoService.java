package com.agenda.agenda.service;

import java.util.ArrayList;
import java.util.List;
import com.agenda.agenda.model.Agendamento;
import com.agenda.agenda.model.StatusAgendamentos;
import com.agenda.agenda.repository.AgendamentoRepository;

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
                a.getHora().equals(agendamento.getHora())) {
                
            throw new RuntimeException("Horário ocupado!");
            }
        }

        return repository.save(agendamento);
    }
    

     public void confirmar(Integer id){
        List<Agendamento> lista = repository.findAll();

        for(Agendamento a : lista){
            if(a.getId() == id){
                a.setStatus(StatusAgendamentos.CONFIRMADO);
                return;
                }
            }
    }

    public boolean verificar(Integer id){
        return repository.existsById(id);
    }

    public void cancelar(Integer id) {

              if (!repository.existsById(id)) {
                throw new RuntimeException("Agendamento não encontrado");
              }
        
              repository.deleteById(id);
    }

}
