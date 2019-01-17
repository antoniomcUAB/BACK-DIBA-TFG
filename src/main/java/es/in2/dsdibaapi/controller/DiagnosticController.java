package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.service.DiagnosticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Diagnostic")
public class DiagnosticController {
	
	@Autowired
	private DiagnosticService diagnosticService;
	
	
	
	@RequestMapping(value = "/diagnostic/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta d'un diagnóstic", notes = "")
	  public Diagnostic getDiagnostic(@PathVariable Long id) {
	     
		return diagnosticService.findById(id);
	  }

	
	@RequestMapping(value = "/diagnostics/{expedient}/{entorn}", method = RequestMethod.GET)
	@ApiOperation(value = "Diagnóstics per entorn", notes = "")
	  public Iterable<Diagnostic> getDiagnostic(@PathVariable Long expedient,@PathVariable Long entorn) {
		return diagnosticService.findByExpedientEntorn(expedient,entorn);
	  }
	
	
	@RequestMapping(value = "/diagnostic/{expedient}/{entorn}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta/modificació d'un diagnóstic", notes = "")
	  public Diagnostic putDiagnostic(@PathVariable Long expedient,@PathVariable Long entorn,@RequestBody Diagnostic diagnostic) {
		return diagnosticService.save(diagnostic,expedient,entorn);
	  }
	
}
