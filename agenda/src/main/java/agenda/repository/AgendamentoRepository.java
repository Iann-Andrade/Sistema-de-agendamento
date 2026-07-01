package agenda.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import agenda.model.Agendamento;

import java.util.*;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento>findByData(LocalDate data);
    
}

