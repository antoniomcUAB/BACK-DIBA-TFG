package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.FrequenciaGravetat;

public interface FrequenciaGravetatService {
	
	public List<FrequenciaGravetat> findAll();	

	public FrequenciaGravetat save(FrequenciaGravetat frequencia);

}
