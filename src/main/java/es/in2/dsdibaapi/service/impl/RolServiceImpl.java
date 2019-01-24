package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Rol;
import es.in2.dsdibaapi.repository.RolRepository;
import es.in2.dsdibaapi.service.RolService;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	RolServiceImpl() {}
	
	@Autowired
	RolRepository rolRepository;
	
	@Cacheable("rol")
    public Rol findById(Long id) {
		return rolRepository.findById(id).get();
    }
	

	public Rol save(Rol v) {
		return rolRepository.save(v);
    }
	

}
