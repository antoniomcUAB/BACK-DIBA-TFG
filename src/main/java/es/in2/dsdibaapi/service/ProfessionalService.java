package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.repository.ProfessionalRepository;

@Service
public class ProfessionalService {
	
	@Autowired
	ProfessionalService() {}
	
	@Autowired
	ProfessionalRepository professionalRepository;
	
	@Cacheable("professional")
    public Professional findById(Long id) {
		return professionalRepository.findById(id).get();
    }
	

	public Professional save(Professional v) {
		return professionalRepository.save(v);
    }
	

}
