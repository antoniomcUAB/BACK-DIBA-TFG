package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.repository.PersonaRepository;
import es.in2.dsdibaapi.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	PersonaServiceImpl() {}
	
	@Autowired
	PersonaRepository personaRepository;
	
	@Cacheable("persona")
    public Persona findById(Long id) {
		return personaRepository.findById(id).get();
    }
	
	@Cacheable("personas")
    public List<Persona> findAll() {
		return personaRepository.findAll();
    }
	

	public Persona save(Persona v) {
		return personaRepository.save(v);
    }
	

}
