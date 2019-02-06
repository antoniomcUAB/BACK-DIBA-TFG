package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.repository.AmbitRepository;
import es.in2.dsdibaapi.service.AmbitService;

@Service
public class AmbitServiceImpl implements AmbitService {
	
	@Autowired
	AmbitServiceImpl() {}
	
	@Autowired
	AmbitRepository ambitRepository;
	
	@Cacheable("ambit")
    public Ambit save(Ambit ambit) {
		return ambitRepository.save(ambit);
    }
	
	@Cacheable("ambit")
    public Ambit findById(Long id) {
		return ambitRepository.findById(id).get();
    }
	
    public List<Ambit> findAll() {
		return ambitRepository.findAll();
    }
	
}
