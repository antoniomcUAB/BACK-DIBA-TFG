package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Rol;

public interface RolService {
	
    public Rol findById(Long id);
    
	public Rol save(Rol v);
	
	public Rol findByCodi(Long codi);
	

}
