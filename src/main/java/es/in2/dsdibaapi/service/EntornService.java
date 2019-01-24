package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Entorn;

public interface EntornService {
	
    public Entorn findById(Long id) ;
	
	public List<Entorn> findAll() ;
	
	public Entorn save(Entorn entorn) ;
	
}
