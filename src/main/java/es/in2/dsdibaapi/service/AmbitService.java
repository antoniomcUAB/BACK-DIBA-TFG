package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Ambit;

public interface AmbitService {	
	
    public Ambit save(Ambit ambit);
	
	
    public List<Ambit> findAll();
	
}
