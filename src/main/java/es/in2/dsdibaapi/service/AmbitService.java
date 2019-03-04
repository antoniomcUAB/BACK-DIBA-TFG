package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Ambit;

public interface AmbitService {	
	
    public Ambit save(Ambit ambit);
	
    public Ambit findById(Long id);
	
    public Iterable<Ambit> findAll(Long versio);
	
}
