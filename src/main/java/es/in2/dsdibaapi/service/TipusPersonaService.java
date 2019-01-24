package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.TipusPersona;

public interface TipusPersonaService {
	
    public TipusPersona findById(Long id);
    
    public List<TipusPersona> findAll();

	public TipusPersona save(TipusPersona v);
	

}
