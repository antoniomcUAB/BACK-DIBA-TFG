package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Avaluacio;
import es.in2.dsdibaapi.repository.AvaluacioRepository;
import es.in2.dsdibaapi.service.AvaluacioService;

@Service
public class AvaluacioServiceImpl implements AvaluacioService {
	
	@Autowired
	AvaluacioServiceImpl() {}
	
	@Autowired
	AvaluacioRepository avaluacioRepository;
	
		
	public Avaluacio save(Avaluacio a) {
		return avaluacioRepository.save(a);
    }
	
}
