package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.repository.PersonaRepository;

@Service
public class PersonaService {
	
	@Autowired
	PersonaService() {}
	
	@Autowired
	PersonaRepository personaRepository;
	
	@Cacheable("persona")
    public Persona findById(Long id) {
		return personaRepository.findById(id).get();
    }
	

	public Persona save(Persona v) {
		return personaRepository.save(v);
    }
	

}
