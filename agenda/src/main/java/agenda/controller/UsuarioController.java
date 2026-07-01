package agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agenda.model.Usuario;
import agenda.repository.UsuarioRepository;
import agenda.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioRepository repository;

    
    @PostMapping("/cadastrar-usuario")
    public Usuario cadastrar (@RequestBody Usuario usuario){
        return service.cadastrar(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody Usuario usuario){
        System.out.println("vamos ");

        Usuario usuarioLogado = service.login(usuario);

        System.out.println("Usuario logado"+usuarioLogado);

        if (usuarioLogado != null) {

            System.out.println("Aqui é o retorno do usuasio logado: " + usuario);
            return ResponseEntity.ok(usuarioLogado);
        }

        System.out.println("chegou no retorno negativo de login");
        return ResponseEntity.badRequest().body("E-mail ou senha inválidos.");
    }



}
