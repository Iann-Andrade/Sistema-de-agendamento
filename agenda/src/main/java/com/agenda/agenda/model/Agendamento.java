package com.agenda.agenda.model;

    
    

public class Agendamento {

    private String nomeCliente;
    private int id;
    private String data;
    private String hora;
    private String descricao;
    private StatusAgendamentos status;


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

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public StatusAgendamentos getStatus(){
        return status;
    }

    public void setStatus(StatusAgendamentos status){
        this.status = status;
    };

    

}

    
