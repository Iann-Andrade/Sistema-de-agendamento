package agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import agenda.model.Usuario;
import jakarta.persistence.Id;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //Usuario findByEmailUsuario(String emailUsuario);
}
