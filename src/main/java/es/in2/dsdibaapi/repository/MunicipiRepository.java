package es.in2.dsdibaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import es.in2.dsdibaapi.model.Municipi;

public interface MunicipiRepository extends JpaRepository<Municipi, Long>, QuerydslPredicateExecutor<Municipi>  {
	/*@Query("select cucc_municipis.mun_id id,cucc_municipis.mun_nom descripcio from cucc_ens,cucc_municipis where  cucc_ens.ens_mun = cucc_municipis.mun_id and ens_ine = ?1")
	MunicipiVUS findMunicipi(Long municipi);*/
}
