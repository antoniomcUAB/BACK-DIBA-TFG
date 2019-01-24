package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Valoracio;

public interface ValoracioService {
	
    public Valoracio findById(Long id) ;
	
	public Valoracio save(Valoracio v);
	
}
