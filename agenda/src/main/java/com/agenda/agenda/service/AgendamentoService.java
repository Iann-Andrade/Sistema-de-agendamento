package com.agenda.agenda.service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.agenda.agenda.model.Agendamento;
import com.agenda.agenda.model.StatusAgendamentos;
import com.agenda.agenda.repository.AgendamentoRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class AgendamentoService {
    
    @Autowired
    private AgendamentoRepository repository;

     public List<Agendamento> listar() {
         return repository.findAll();
     }

     //listar horarios disponíveis
     public List<String> buscarHorariosDisponiveis(@RequestParam LocalDate data) {
        List<String> horariosPadrao = List.of(
            "08:00",
            "08:30",
            "09:00",
            "09:30",
            "10:00",
            "10:30",
            "11:00",
            "11:30",
            "12:00",
            "12:30",
            "13:00",
            "13:30",
            "14:00",
            "14:30",
            "15:00",
            "15:30",
            "16:00",
            "16:30",
            "17:00",
            "17:30",
            "18:00"
        );

        System.out.println("Entrou no service");


        List<Agendamento> ocupados = repository.findByData(data);

        System.out.println("Encontrou " +
        ocupados.size() +
        " agendamentos");

        List<String> horasOcupadas =    
                    ocupados.stream()  
                        .map(Agendamento::getHora)
                        .toList();

        List<String> horariosDisponiveis =
            new ArrayList<>();

            for(String horario : horariosPadrao){
                if(!horasOcupadas.contains(horario)){
                    horariosDisponiveis.add(horario);
                } 
            }

            System.out.println(horariosDisponiveis);
            
            return horariosDisponiveis;
     };



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
