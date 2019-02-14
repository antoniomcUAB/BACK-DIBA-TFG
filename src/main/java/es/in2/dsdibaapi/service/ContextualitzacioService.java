package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Contextualitzacio;

public interface ContextualitzacioService {
	
	
	
    public Contextualitzacio findById(Long id) ;

    public void delete(Long id);
	
    public Iterable<Contextualitzacio> findByDiagnosticAmbit(Long expedient,Long ambit);
	
	public Contextualitzacio save (Contextualitzacio contextualitzacio, Long expedient, Long factor);
	
	public Contextualitzacio save (Contextualitzacio contextualitzacio);	

}

