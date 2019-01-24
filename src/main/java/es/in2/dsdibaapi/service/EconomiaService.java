package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Economia;


public interface EconomiaService {
	

    public Economia findById(Long id);
		
	public List<Economia> findAll();
	
	public Economia save(Economia economia);
	
	public void delete(Economia economia);
	
}
