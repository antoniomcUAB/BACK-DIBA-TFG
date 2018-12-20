package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.repository.DiagnosticRepository;

@RestController
public class DiagnosticController {
	
	@Autowired
	private DiagnosticRepository diagnosticRepository;
	
	
	
	@RequestMapping(value = "/diagnostic", method = RequestMethod.PUT)
	  public Diagnostic putModel(@RequestBody Diagnostic diagnostic) {
	     
		Diagnostic newDiagnostic = diagnosticRepository.save(diagnostic);
	    
	    return newDiagnostic;
	  }
	
	@RequestMapping(value = "/diagnostic/{id}", method = RequestMethod.GET)
	  public Diagnostic getModel(@PathVariable Long id) {
	     
		Diagnostic diagnostic = diagnosticRepository.findOne(id);
	    
	    return diagnostic;
	  }

}
