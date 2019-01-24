package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.TipusPersona;
import es.in2.dsdibaapi.repository.TipusPersonaRepository;
import es.in2.dsdibaapi.service.TipusPersonaService;

@Service
public class TipusPersonaServiceImpl implements TipusPersonaService{
	
	@Autowired
	TipusPersonaServiceImpl() {}
	
	@Autowired
	TipusPersonaRepository tipusPersonaRepository;
	
	@Cacheable("tipusPersona")
    public TipusPersona findById(Long id) {
		return tipusPersonaRepository.findById(id).get();
    }
	
	@Cacheable("tipusPersona")
    public List<TipusPersona> findAll() {
		return tipusPersonaRepository.findAll();
    }
	

	public TipusPersona save(TipusPersona v) {
		return tipusPersonaRepository.save(v);
    }


}
