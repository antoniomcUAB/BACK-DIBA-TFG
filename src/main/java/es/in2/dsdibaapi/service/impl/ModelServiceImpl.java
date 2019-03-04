package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Model;
import es.in2.dsdibaapi.model.QAmbit;
import es.in2.dsdibaapi.model.QFactor;
import es.in2.dsdibaapi.model.QFactorEconomic;
import es.in2.dsdibaapi.repository.AmbitRepository;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;
import es.in2.dsdibaapi.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService{
	
	@Autowired
	ModelServiceImpl() {}
	
	@Autowired
	private AmbitRepository ambitRepository;
		
	@Autowired
	private FactorEconomicRepository factorEconomicRepository;
	
	@Cacheable("model")
    public Model findByVersion(Long versio) {
		Predicate predicateAmbit = null;
		Predicate predicateFactor = null;
		
		
		predicateAmbit = QAmbit.ambit.versioModel.id.eq(versio);
		predicateFactor = QFactorEconomic.factorEconomic.versioModel.id.eq(versio);		
		
	    Model model = new Model();
	    model.setAmbits(ambitRepository.findAll(predicateAmbit,new Sort(Sort.Direction.ASC, "id")));
	    model.setFactorEconomic(factorEconomicRepository.findAll(predicateFactor));  
	    
	    
	    return model;
    }
	

	
	
	

}
