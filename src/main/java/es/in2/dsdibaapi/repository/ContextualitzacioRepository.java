package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import es.in2.dsdibaapi.model.Contextualitzacio;

public interface ContextualitzacioRepository extends JpaRepository<Contextualitzacio, Long> 
									, QueryDslPredicateExecutor<Contextualitzacio>  {
	
}
