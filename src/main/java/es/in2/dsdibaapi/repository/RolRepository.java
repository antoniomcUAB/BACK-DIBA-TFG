package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import es.in2.dsdibaapi.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> , QuerydslPredicateExecutor<Rol>{
	public Rol findByCodi (Long codi);
}
