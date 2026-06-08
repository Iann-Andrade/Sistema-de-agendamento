package com.agenda.agenda.repository;

import com.agenda.agenda.model.Agendamento;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento>findByData(LocalDate data);
    
}

