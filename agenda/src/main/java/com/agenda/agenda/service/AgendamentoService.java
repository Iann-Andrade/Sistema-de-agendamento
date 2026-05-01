package com.agenda.agenda.service;

import java.util.ArrayList;
import java.util.List;
import com.agenda.agenda.model.Agendamento;
import com.agenda.agenda.model.StatusAgendamentos;
import com.agenda.agenda.controller.StatusController;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    
    private List<Agendamento> lista = new ArrayList<>();
    private int contador = 1;

     public List<Agendamento> listar() {
         return lista;
     }
 
     public Agendamento criar(Agendamento agendamento){

        
            for(Agendamento a : lista){
                if (a.getData().equals(agendamento.getData()) &&
                a.getHora().equals(agendamento.getHora())) {
                
            throw new RuntimeException("Horário ocupado!");
            }
        }

        agendamento.setId(contador++);
        lista.add(agendamento);

        return agendamento;
    }

    public String remover(int id) {

        for (Agendamento a : lista) {
            if (a.getId() == id) {
                lista.remove(a);
                return "Removido!";
            }
        }

        return "ID não encontrado!";
    }
    

     public void confirmar(int id){
        for(Agendamento a : lista){
            if(a.getId() == id){
                a.setStatus(StatusAgendamentos.CONFIRMADO);
                return;
                }
            }
    }

    public void cancelar(int id) {
        lista.get(id).setStatus(StatusAgendamentos.CANCELADO);
    }

}
