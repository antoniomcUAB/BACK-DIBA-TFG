package es.in2.dsdibaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import es.in2.dsdibaapi.model.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long>, QuerydslPredicateExecutor<Professional>  {
	//public Optional<Professional> findByUsername (String username);
}
