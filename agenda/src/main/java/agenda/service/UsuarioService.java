package agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agenda.controller.UsuarioController;
import agenda.model.Usuario;
import agenda.repository.UsuarioRepository;

import java.util.ArrayList;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.val;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;

   
    public Usuario cadastrar(Usuario usuario){

      List<Usuario> listaUsuario = usuarioRepository.findAll();

      String email = usuario.getEmailUsuario();
      String senha = usuario.getSenhaUsuario();
      Integer id = usuario.getId();
      

      //Checa se o campo de e-mail está vazio
      if(usuario.getEmailUsuario() == null||usuario.getEmailUsuario().isEmpty()){
          System.out.println("Você deve preencher o campo de E-mail.");
          throw new IllegalArgumentException("Você deve preencher o campo de E-mail.");
      }

      //Checa se o campo de senha do usuario está vazio está vazio
      if(usuario.getSenhaUsuario() == null || usuario.getSenhaUsuario().isEmpty()){
          System.out.println("O campo de senha está vazio, preencha para seguirmos com o cadastro.");
          throw new IllegalArgumentException("O campo de senha está vazio, preencha para seguirmos com o cadastro.");
      }


        for(Usuario usuarioAtual : listaUsuario){

        if(usuarioAtual.getEmailUsuario().equalsIgnoreCase(email)){
            System.out.println("E-mail cadastrado.");

            return null;
        }

            usuario.setSenhaUsuario(senha);
            usuario.setId(id);
        }
        
            Usuario usuarioSalvo = usuarioRepository.save(usuario);

            return usuarioSalvo;

    }

    public Usuario   login(Usuario usuario){
        System.out.println("vamos validar");


        List<Usuario> listaUsuario = usuarioRepository.findAll();

      String email = usuario.getEmailUsuario();
      String senha = usuario.getSenhaUsuario();
      Integer id = usuario.getId();
      
      for(Usuario usuarioAtual : listaUsuario){

            if(usuarioAtual.getEmailUsuario().equalsIgnoreCase(email) && usuarioAtual.getSenhaUsuario().equals(senha)){

                System.out.println("E-mail e senhea validados");

               return usuarioAtual;
            }
        }

        System.out.println("E-mail e senhe negados");

      return null;

    }

}
