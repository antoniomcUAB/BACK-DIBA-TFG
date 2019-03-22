package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.repository.ProfessionalRepository;
import es.in2.dsdibaapi.service.ProfessionalService;

@Service
public class ProfessionalServiceImpl implements ProfessionalService {
	
	@Autowired
	ProfessionalServiceImpl() {}
	
	@Autowired
	ProfessionalRepository professionalRepository;
	
	/*
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	*/
	public Professional save(Professional v) {
		//v.setPassword(bCryptPasswordEncoder.encode(v.getPassword()));
		return professionalRepository.save(v);
    }
	
	
	@Cacheable("professional")
    public Professional findById(Long id) {
		return professionalRepository.findById(id).get();
    }
	
	@Cacheable("professional")
    public Professional findByUsername(String username) {
		return professionalRepository.findByUsername(username).get();
    }
	

	public List<Professional> findAll() {
		return professionalRepository.findAll();
    }
	

}
