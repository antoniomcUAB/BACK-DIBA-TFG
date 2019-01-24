package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Criteri;
import es.in2.dsdibaapi.repository.CriteriRepository;
import es.in2.dsdibaapi.service.CriteriService;

@Service
public class CriteriServiceImpl implements CriteriService{
	
	@Autowired
	CriteriServiceImpl() {}
	
	@Autowired
	CriteriRepository criteriRepository;
	
	@Cacheable("criteri")
    public List<Criteri> findAll() {
		return criteriRepository.findAll();
    }
	

	public Criteri save(Criteri criteri) {
		return criteriRepository.save(criteri);
    }

}
