package es.in2.dsdibaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.repository.EntornRepository;

@Service
public class EntornService {
	
	@Autowired
	EntornService() {}
	
	@Autowired
	EntornRepository entornRepository;
	
	@Cacheable("entorn")
    public Entorn findById(Long id) {
		return entornRepository.findById(id).get();
    }
	
	@Cacheable("entorn")
	public List<Entorn> findAll() {
		return entornRepository.findAll();
    }
	
	public Entorn save(Entorn entorn) {
		return entornRepository.save(entorn);
    }
	
}
