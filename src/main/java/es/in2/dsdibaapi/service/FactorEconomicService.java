package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.FactorEconomic;

public interface FactorEconomicService {
	
    public FactorEconomic findById(Long id) ;
	
	public FactorEconomic save(FactorEconomic factor) ;
	

}
