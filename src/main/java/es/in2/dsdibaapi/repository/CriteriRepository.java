package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import es.in2.dsdibaapi.model.Criteri;

public interface CriteriRepository extends JpaRepository<Criteri, Long>, QuerydslPredicateExecutor<Criteri> {

}
