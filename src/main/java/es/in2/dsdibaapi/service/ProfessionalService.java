package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Professional;

public interface ProfessionalService {
	
    public Professional findById(Long id) ;
    
    public Professional findByUsername(String username) ;

	public Professional save(Professional v);
	
	public List<Professional> findAll() ;
	

}
