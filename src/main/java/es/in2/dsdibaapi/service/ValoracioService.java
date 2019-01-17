package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Valoracio;
import es.in2.dsdibaapi.repository.ValoracioRepository;

@Service
public class ValoracioService {
	
	@Autowired
	ValoracioService() {}
	
	@Autowired
	ValoracioRepository valoracioRepository;
	
	@Cacheable("valoracio")
    public Valoracio findById(Long id) {
		return valoracioRepository.findById(id).get();
    }
	
	public Valoracio save(Valoracio v) {
		return valoracioRepository.save(v);
    }
	
}
