package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Avaluacio;
import es.in2.dsdibaapi.repository.AvaluacioRepository;

@Service
public class AvaluacioService {
	
	@Autowired
	AvaluacioService() {}
	
	@Autowired
	AvaluacioRepository avaluacioRepository;
	
	
	public Avaluacio save(Avaluacio a) {
		return avaluacioRepository.save(a);
    }
	
}
