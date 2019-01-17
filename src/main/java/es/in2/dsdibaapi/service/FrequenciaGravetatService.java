package es.in2.dsdibaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.FrequenciaGravetat;
import es.in2.dsdibaapi.repository.FrequenciaGravetatRepository;

@Service
public class FrequenciaGravetatService {
	
	@Autowired
	FrequenciaGravetatService() {}
	
	@Autowired
	FrequenciaGravetatRepository frequenciaRepository;
	
	@Cacheable("frequencia_gravetat")
    public List<FrequenciaGravetat> findAll() {
		return frequenciaRepository.findAll();
    }
	

	public FrequenciaGravetat save(FrequenciaGravetat frequencia) {
		return frequenciaRepository.save(frequencia);
    }

}
