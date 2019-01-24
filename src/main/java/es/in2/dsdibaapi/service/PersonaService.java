package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Persona;

public interface PersonaService {
	
    public Persona findById(Long id);
	
    public List<Persona> findAll();	

	public Persona save(Persona v);

}
