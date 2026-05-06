package com.agenda.agenda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "agendamento")
public class AgendaEnity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nomeCliente;
    
    @Column(length = 10)
    private String data;

    @Column(length = 10)
    private String hora;
     
    @Column(length = 100)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusAgendamentos status;


}
