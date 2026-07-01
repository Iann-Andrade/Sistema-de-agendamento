package agenda.model;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.annotation.Generated;
import jakarta.persistence.*;

@Entity
@Table(name = "agendamento")
public class Agendamento {  
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeCliente;
    private LocalDate data;
    private String hora;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusAgendamentos statusDescricao;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public StatusAgendamentos getStatusDescricao(){
        return statusDescricao;
    }

    public void setStatusDescricao(StatusAgendamentos statusDescricao){
        this.statusDescricao = statusDescricao;
    }


    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

    
