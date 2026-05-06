package com.agenda.agenda.repository;

import com.agenda.agenda.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

}

