package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.model.QFrequencia;
import es.in2.dsdibaapi.repository.FrequenciaRepository;
import es.in2.dsdibaapi.service.FrequenciaService;

@Service
public class FrequenciaServiceImpl implements FrequenciaService {
	
	
	@Autowired
	FrequenciaServiceImpl() {}
	
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
	
	@Cacheable("frequencia")
	public Frequencia findByDescription(String description) {

		Predicate predicate = QFrequencia.frequencia.descripcio.equalsIgnoreCase(description);
		
		return frequenciaRepository.findOne(predicate).get();
	}
	

	public Frequencia save(Frequencia frequencia) {
		return frequenciaRepository.save(frequencia);
    }

}
