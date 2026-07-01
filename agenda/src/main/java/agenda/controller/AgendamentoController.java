package agenda.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import agenda.model.Agendamento;
import agenda.repository.AgendamentoRepository;
import agenda.service.AgendamentoService;


@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService service;
    @Autowired   
    private AgendamentoRepository repository;

    //Lista os agendamentos criados, separar essa função apenas para a página do ADM
    @GetMapping
    public List<Agendamento> listar(){
        System.out.println("listarrr");
        return service.listar();
    }

    //Chama o criar no service
    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento){
        return service.criar(agendamento);
    };

    //Função de lista dos agendamentos disponíveis
    @GetMapping("/horarios-disponiveis")
        public List<String> horariosDisponiveis(@RequestParam LocalDate data){

            System.out.println("DATA RECEBIDA: " + data);

            return service.buscarHorariosDisponiveis(data);
        };

        //Criar área de agendamentos do usuário
        /*@GetMapping
        public List<String> meusAgendamentos(@RequestParam Agendamento agendamento){

            System.out.println("Listando os agendamentos do usuário");
            return service.buscarMeusAgendamentos(agendamento);
        };*/

    

    
}
