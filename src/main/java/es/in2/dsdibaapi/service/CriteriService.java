package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Criteri;

public interface CriteriService {
	
    public List<Criteri> findAll() ;
	

	public Criteri save(Criteri criteri);

}
