package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.in2.dsdibaapi.model.Diagnostic;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {

}
