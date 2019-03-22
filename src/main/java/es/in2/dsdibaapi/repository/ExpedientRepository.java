package es.in2.dsdibaapi.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.in2.dsdibaapi.model.Expedient;

public interface ExpedientRepository extends PagingAndSortingRepository<Expedient, Long> , QuerydslPredicateExecutor<Expedient> {

}
