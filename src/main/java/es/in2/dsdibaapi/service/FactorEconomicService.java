package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;

@Service
public class FactorEconomicService {
	
	@Autowired
	FactorEconomicService() {}
	
	@Autowired
	FactorEconomicRepository factorRepository;
	
	@Cacheable("factor_economic")
    public FactorEconomic findById(Long id) {
		return factorRepository.findById(id).get();
    }
	
	public FactorEconomic save(FactorEconomic factor) {
		return factorRepository.save(factor);
    }
	

}
