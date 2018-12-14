package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import es.in2.dsdibaapi.model.Entorn;

public interface EntornRepository extends JpaRepository<Entorn, Long> , QueryDslPredicateExecutor<Entorn> {

}
