package es.in2.dsdibaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.repository.AmbitRepository;

@RestController
public class ModelController {
	
	@Autowired
	private AmbitRepository ambitRepository;
	
	@RequestMapping(value = "/model", method = RequestMethod.GET)
	  public List<Ambit> getModel() {
	    return  ambitRepository.findAll();
	  }

}
