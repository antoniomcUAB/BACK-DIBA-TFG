package es.in2.dsdibaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> , QuerydslPredicateExecutor<Diagnostic> {	
	List<Diagnostic> findByExpedientAndEntorn(Expedient expedient, Entorn entorn);
}
