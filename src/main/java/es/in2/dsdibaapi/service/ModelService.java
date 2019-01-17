package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Model;
import es.in2.dsdibaapi.model.QAmbit;
import es.in2.dsdibaapi.model.QFactorEconomic;
import es.in2.dsdibaapi.repository.AmbitRepository;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;

@Service
public class ModelService {
	
	@Autowired
	ModelService() {}
	
	@Autowired
	private AmbitRepository ambitRepository;
		
	@Autowired
	private FactorEconomicRepository factorEconomicRepository;
	
	@Cacheable("model")
    public Model findByVersion(Long versio) {
		Predicate predicateAmbit = null;
		Predicate predicateFactor = null;
		
		
		predicateAmbit = QAmbit.ambit.entorn.any().situacioSocial.any().versioModel.ID.eq(versio);
		predicateFactor = QFactorEconomic.factorEconomic.versioModel.ID.eq(versio);		
		
	    Model model = new Model();
	    model.setAmbits(ambitRepository.findAll(predicateAmbit));
	    model.setFactorEconomic(factorEconomicRepository.findAll(predicateFactor));  
	    
	    
	    return model;
    }
	

	
	
	

}
