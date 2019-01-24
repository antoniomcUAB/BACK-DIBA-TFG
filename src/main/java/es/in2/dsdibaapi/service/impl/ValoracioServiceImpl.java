package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Valoracio;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.repository.ValoracioRepository;
import es.in2.dsdibaapi.service.ValoracioService;

@Service
public class ValoracioServiceImpl implements ValoracioService{
	
	@Autowired
	ValoracioServiceImpl() {}
	
	@Autowired
	ValoracioRepository valoracioRepository;
	
	@Autowired
	ExpedientRepository expedientRepository;
	
	@Cacheable("valoracio")
    public Valoracio findById(Long id) {
		return valoracioRepository.findById(id).get();
    }
	
	public Valoracio save(Valoracio v) {		
		return valoracioRepository.save(v);
    }
	
}
