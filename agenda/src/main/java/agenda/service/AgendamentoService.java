package agenda.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event.ID;

import agenda.model.Agendamento;
import agenda.model.StatusAgendamentos;
import agenda.model.Usuario;
import agenda.repository.AgendamentoRepository;
import agenda.repository.UsuarioRepository;


@RestController
@Service
public class AgendamentoService {
    
    @Autowired
    private AgendamentoRepository repository;

    private UsuarioRepository usuarioRepository;
    
    @ManyToOne 
    private Usuario usuario;

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


     //Cria o agendamento
     public Agendamento criar(Agendamento agendamento){


    System.out.println("Agendamento recebido: " + agendamento);

    System.out.println("Usuario recebido: " + agendamento.getUsuario());

        List<Agendamento> lista = repository.findAll();

        if(agendamento.getUsuario()== null){
            throw new RuntimeException("Usuário não identificado");            
        };

        Integer usuarioId = agendamento.getUsuario().getId();

            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            agendamento.setUsuario(usuario);

            for(Agendamento a : lista){
                if (a.getData().equals(agendamento.getData()) &&
                a.getHora().equals(agendamento.getHora())) 
                {
                    throw new RuntimeException("Horário ocupado!");
                }
            }
            
        System.out.println(usuarioId);
        agendamento.setStatusDescricao(StatusAgendamentos.PENDENTE);
        System.out.println(agendamento.getStatusDescricao());

        return repository.save(agendamento);
    }
    
    //Confirma o agendamento
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

    //Verifica se o ID existe no banco de dados
    public boolean verificar(Integer id){
        return repository.existsById(id);
    }

    //Cancela o agendamento e deleta do banco de dados
    public String cancelar(Integer id) {
        
              if (!repository.existsById(id)) {
                throw new RuntimeException("Agendamento não encontrado");
              }
              
              repository.deleteById(id);
              return ("Agendamento cancelado com sucesso!");
            };

    //Cria liste dos meus agendamentos do usuário
    //public List<Agendamento> meusAgendamentos(){}
            
    }
