package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Economia;
import es.in2.dsdibaapi.repository.EconomiaRepository;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EconomiaService;

@Service
public class EconomiaServiceImpl implements EconomiaService	{
	
	@Autowired
	EconomiaServiceImpl() {}
	
	@Autowired
	EconomiaRepository economiaRepository;
	
	@Autowired
	DiagnosticService diagnosticService;
	
	@Cacheable("economia")
    public Economia findById(Long id) {
		return economiaRepository.findById(id).get();
    }
	
	@Cacheable("economia")
	public List<Economia> findAll() {
		return economiaRepository.findAll();
    }
	
	public Economia save(Economia economia) {
		return economiaRepository.save(economia);
    }
	
	public void delete(Economia economia) {
		economiaRepository.delete(economia);
    }
	
}
