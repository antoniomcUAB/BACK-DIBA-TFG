package es.in2.dsdibaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Criteri;
import es.in2.dsdibaapi.repository.CriteriRepository;

@Service
public class CriteriService {
	
	@Autowired
	CriteriService() {}
	
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
