package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Municipi;
import es.in2.dsdibaapi.repository.MunicipiRepository;

@Service
public class MunicipiService {
	
	@Autowired
	MunicipiService() {}
	
	@Autowired
	MunicipiRepository municipiRepository;
	
	@Cacheable("municipi")
    public Municipi findById(Long id) {
		return municipiRepository.findById(id).get();
    }
	

	public Municipi save(Municipi v) {
		return municipiRepository.save(v);
    }
	

}
