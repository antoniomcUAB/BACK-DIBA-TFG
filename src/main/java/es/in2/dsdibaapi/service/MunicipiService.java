package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Municipi;

public interface MunicipiService {
	
    public Municipi findById(Long id) ;

    public Municipi findByName(String municipiName) ;

	public Municipi save(Municipi v) ;


}
