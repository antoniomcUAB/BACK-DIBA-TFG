package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import es.in2.dsdibaapi.model.FactorGravetat;

public interface FactorGravetatRepository extends JpaRepository<FactorGravetat, Long>  
				, QueryDslPredicateExecutor<FactorGravetat> {
	
}
