package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Model;
import es.in2.dsdibaapi.repository.AmbitRepository;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servei Model")
public class ModelController {
	
	@Autowired
	private AmbitRepository ambitRepository;
	
	@Autowired
	private FactorEconomicRepository factorEconomicRepository;
	
	
	
	@RequestMapping(value = "/model", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta del model", notes = "")
	  public Model getModel() {
	    Model model = new Model();
	    model.setAmbits(ambitRepository.findAll());
	    model.setFactorEconomic(factorEconomicRepository.findAll());  
	    
	    
	    return model;
	  }

}
