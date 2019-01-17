package es.in2.dsdibaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.repository.AmbitRepository;

@Service
public class AmbitService {
	
	@Autowired
	AmbitService() {}
	
	@Autowired
	AmbitRepository ambitRepository;
	
	@Cacheable("ambit")
    public Ambit save(Ambit ambit) {
		return ambitRepository.save(ambit);
    }
	
	
    public List<Ambit> findAll() {
		return ambitRepository.findAll();
    }
	
}
