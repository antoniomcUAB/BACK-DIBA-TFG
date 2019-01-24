package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Factor;

public interface FactorService {
	
    public Factor findById(Long id);
	
	public Factor save(Factor factor);
	
    public Factor findByDescription(String description);

}
