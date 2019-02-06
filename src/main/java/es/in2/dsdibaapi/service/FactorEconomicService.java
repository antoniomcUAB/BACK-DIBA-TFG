package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.FactorEconomic;

public interface FactorEconomicService {
	
    public FactorEconomic findById(Long id) ;
	
	public FactorEconomic save(FactorEconomic factor) ;
	
	public List<FactorEconomic> findAll();
	
	public void deleteById(Long id) ;
	

}
