package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Estat;

public interface EstatService {
	
    public Estat findById(Long id);
    
	public Estat save(Estat v);
	
	public Estat findByDescripcio(String descripcio);
	

}
