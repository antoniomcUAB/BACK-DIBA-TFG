package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.in2.dsdibaapi.model.Expedient;

public interface ExpedientRepository extends JpaRepository<Expedient, Long> {

}
