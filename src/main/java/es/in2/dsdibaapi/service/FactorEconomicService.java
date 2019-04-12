package es.in2.dsdibaapi.service;

import java.util.Optional;

import es.in2.dsdibaapi.model.FactorEconomic;

public interface FactorEconomicService {
	
    public FactorEconomic findById(Long id) ;
	
	public FactorEconomic save(FactorEconomic factor) ;
	
	public Iterable<FactorEconomic> findAll(Optional<Long> versio);
	
	public void deleteById(Long id) ;
	

}
