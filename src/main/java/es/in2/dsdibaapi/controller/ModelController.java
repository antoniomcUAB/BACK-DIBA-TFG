package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Model;
import es.in2.dsdibaapi.repository.AmbitRepository;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;

@RestController
public class ModelController {
	
	@Autowired
	private AmbitRepository ambitRepository;
	
	@Autowired
	private FactorEconomicRepository factorEconomicRepository;
	
	
	
	@RequestMapping(value = "/model", method = RequestMethod.GET)
	  public Model getModel() {
	    Model model = new Model();
	    model.setAmbits(ambitRepository.findAll());
	    model.setFactorEconomic(factorEconomicRepository.findAll());  
	    
	    
	    return model;
	  }

}
