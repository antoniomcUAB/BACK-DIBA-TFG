package es.in2.dsdibaapi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import es.in2.dsdibaapi.model.Expedient;

public interface ExpedientService {
	
	public enum Estat {
	    INCOMPLET,
	    COMPLET 
	}
	
    public Expedient findById(Long id) ;
	
//	public List<Expedient> findAll() ;
	
	public Expedient save(Expedient expedient) ;
	
	public Page<Expedient> findByMunicipi(Integer page, Integer size,Long municipi, String codi, String professional, String estat, String sort, String dataCreacio, String dataValidacio);
	
	public Iterable<Expedient> findByMunicipi(Long municipi);
	
	public void eval (Expedient e);
}
