package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.repository.EntornRepository;
import es.in2.dsdibaapi.service.EntornService;

@Service
public class EntornServiceImpl implements EntornService {
	
	@Autowired
	EntornServiceImpl() {}
	
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
