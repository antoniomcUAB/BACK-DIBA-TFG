package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import es.in2.dsdibaapi.model.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> , QuerydslPredicateExecutor<Pregunta> {	
}
