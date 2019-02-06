package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Expedient;

public interface ExpedientService {
	
	public enum Estat {
	    INCOMPLET,
	    COMPLET 
	}
	
    public Expedient findById(Long id) ;
	
	public List<Expedient> findAll() ;
	
	public Expedient save(Expedient expedient) ;
	
	public Iterable<Expedient> findByMunicipi(Long municipi);
	
	public void eval (Expedient e);
}
