package agenda.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import agenda.model.Agendamento;
import agenda.model.Usuario;
import agenda.repository.AgendamentoRepository;
import agenda.security.JwtService;
import agenda.service.AgendamentoService;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService service;
    @Autowired   
    private AgendamentoRepository repository;
    @Autowired
    private JwtService jwtService;

    //Lista os agendamentos criados, separar essa função apenas para a página do ADM
    @GetMapping
    public List<Agendamento> listar(){
    
        System.out.println("listar");
        return service.listar();
    }

    //Chama o criar no service
    @PostMapping
    public Agendamento criar(
        @RequestBody Agendamento agendamento,
        HttpServletRequest request
    ){
        String authorization = request.getHeader("Authorization");
    if (authorization == null || !authorization.startsWith("Bearer ")) {
        throw new RuntimeException("Token não fornecido ou inválido");
    }

    String token = authorization.substring(7);
    Integer usuarioId = Integer.valueOf(jwtService.extrairUsuarioId(token));

        return service.criar(agendamento, usuarioId);
    };

    //Função de lista dos agendamentos disponíveis
    @GetMapping("/horarios-disponiveis")
        public List<String> horariosDisponiveis(@RequestParam LocalDate data){

            System.out.println("DATA RECEBIDA: " + data);

            return service.buscarHorariosDisponiveis(data);
        };


        //Criar buscar meus agendamentos
        @GetMapping("buscar-meus-agendamentos")
        public List<Agendamento> buscarMeusAgendamentos(HttpServletRequest request){

            String authorization = request.getHeader("Authorization");

            String token = authorization.substring(7);

            Integer usuarioId = Integer.valueOf(jwtService.extrairUsuarioId(token));

            System.out.println("Chagou ao buscar meus agendamentos.");
            return service.buscarMeusAgendamentos(usuarioId);
        }



        //Criar área de agendamentos do usuário
        @GetMapping("/meus")
        public List<Agendamento> meusAgendamentos(HttpServletRequest request){

            String authorization = request.getHeader("Authorization");
            String token = authorization.substring(7);
            System.out.println("Chamou: meus agendamentos");
            System.out.println("Print do autthorization" + authorization);

            Integer usuarioId =
            Integer.valueOf(jwtService.extrairUsuarioId(token));

            System.out.println("Chegous so Controller listar meus agendamentos");

            return service.listarMeusAgendamentos(usuarioId);
        };

        //Cancelar meus agendamentos
        @DeleteMapping("/buscar-meus-agendamentos/{id}")
        public ResponseEntity<String> cancelarMeuAgendamento (
            @PathVariable Integer id,
            HttpServletRequest request
        ){
            String authorization = request.getHeader("Authorization");
            String token = authorization.substring(7);

            String mensagem = service.cancelarMeuAgendamento(id);

            return ResponseEntity.ok(mensagem);

        }
    
};
