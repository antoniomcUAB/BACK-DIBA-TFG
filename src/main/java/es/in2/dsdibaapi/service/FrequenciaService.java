package es.in2.dsdibaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.repository.FrequenciaRepository;

@Service
public class FrequenciaService {
	
	@Autowired
	FrequenciaService() {}
	
	@Autowired
	FrequenciaRepository frequenciaRepository;
	
	@Cacheable("frequencia")
    public List<Frequencia> findAll() {
		return frequenciaRepository.findAll();
    }
	
	@Cacheable("frequencia")
    public Frequencia findById(Long id) {
		return frequenciaRepository.findById(id).get();
    }
	

	public Frequencia save(Frequencia frequencia) {
		return frequenciaRepository.save(frequencia);
    }

}
