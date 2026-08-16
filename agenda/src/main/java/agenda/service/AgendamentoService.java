package agenda.service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agenda.model.Agendamento;
import agenda.model.StatusAgendamentos;
import agenda.model.Usuario;
import agenda.repository.AgendamentoRepository;
import agenda.repository.UsuarioRepository;
import agenda.security.JwtAuthenticationFilter;


@RestController
@Service
public class AgendamentoService {
    
    @Autowired
    private AgendamentoRepository repository;
    @Autowired
    private JwtAuthenticationFilter authenticator;
    @Autowired
    private UsuarioRepository usuarioRepository;

    

     public List<Agendamento> listar() {
        System.out.println("Criando lista que percorre as datas");
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
     public Agendamento criar(Agendamento agendamento, Integer usuarioId){

       // Integer idUsuario = agendamento.getUsuarioId();

        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    
        List<Agendamento> lista = repository.findAll();


        System.out.println("aqui está o id do usuario "+ usuarioId);

            for(Agendamento a : lista){
                if (a.getData().equals(agendamento.getData()) &&
                a.getHora().equals(agendamento.getHora())) 
                {
                    throw new RuntimeException("Horário ocupado!");
                }
            }

        agendamento.setUsuario(usuario);
        agendamento.setStatusDescricao(StatusAgendamentos.PENDENTE);
        
        System.out.println(agendamento.getStatusDescricao());

        return repository.save(agendamento);
    }

    //Buscar meus agendamentos
    public List<Agendamento> buscarMeusAgendamentos(Integer usuarioId){

        List<Agendamento> meusAgendamentos = repository.findByUsuario_Id(usuarioId);

        System.out.println("Está no service do retorno de meus agendamentos" + meusAgendamentos.size());
        return meusAgendamentos;

    }


    //Criar lista Meus Agendamentos ainda não está em uso, rever
    public List<Agendamento> listarMeusAgendamentos(Integer usuarioId) {

        List<Agendamento> meusAgendamentos = repository.findByUsuario_Id(usuarioId);

        return meusAgendamentos;
    }
    
    //Cria função de candelar Meu agendamento
    public String cancelarMeuAgendamento(Integer id){

        if (!repository.existsById(id)) {
            throw new RuntimeException("Agendamento não encontrado");
          }
          
          repository.deleteById(id);
          System.out.println("Servie: Cancelar Meus Agendamentos");

        return "Cancelamento do agendamento realizado com sucesso!";
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
              return cancelar(id);
            };

    //Cria liste dos meus agendamentos do usuário
    //public List<Agendamento> meusAgendamentos(){}
            
    }
