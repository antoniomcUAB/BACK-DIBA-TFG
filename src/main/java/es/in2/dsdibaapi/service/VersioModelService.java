package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.VersioModelRepository;

@Service
public class VersioModelService {
	
	@Autowired
	VersioModelService() {}
	
	@Autowired
	VersioModelRepository versioRepository;
	
	@Cacheable("versio")
    public VersioModel findById(Long id) {
		return versioRepository.findById(id).get();
    }
	

	public VersioModel save(VersioModel v) {
		return versioRepository.save(v);
    }
	

}
