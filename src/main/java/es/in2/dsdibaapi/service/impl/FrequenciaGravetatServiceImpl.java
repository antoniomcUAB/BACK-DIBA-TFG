package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.FrequenciaGravetat;
import es.in2.dsdibaapi.repository.FrequenciaGravetatRepository;
import es.in2.dsdibaapi.service.FrequenciaGravetatService;

@Service
public class FrequenciaGravetatServiceImpl implements FrequenciaGravetatService {
	
	@Autowired
	FrequenciaGravetatServiceImpl() {}
	
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
