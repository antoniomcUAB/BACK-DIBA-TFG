package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.core.Evaluation;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.QDiagnostic;
import es.in2.dsdibaapi.repository.DiagnosticRepository;
import es.in2.dsdibaapi.repository.EntornRepository;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Diagnostic")
public class DiagnosticController {
	
	@Autowired
	private DiagnosticRepository diagnosticRepository;
	
	@Autowired
	private ExpedientRepository expedientRepository;
	
	@Autowired
	private EntornRepository entornRepository;
	
	
		
	@Autowired
	private Environment env;
	
	
	
	@RequestMapping(value = "/diagnostic/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta d'un diagnóstic", notes = "")
	  public Diagnostic getDiagnostic(@PathVariable Long id) {
	     
		Diagnostic diagnostic = diagnosticRepository.findOne(id);		
		
		Evaluation.eval(diagnostic, env);
	    
	    return diagnostic;
	  }

	
	@RequestMapping(value = "/diagnostics/{expedient}/{entorn}", method = RequestMethod.GET)
	@ApiOperation(value = "Diagnóstics per entorn", notes = "")
	  public Iterable<Diagnostic> getDiagnostic(@PathVariable Long expedient,@PathVariable Long entorn) {
		
	
		Predicate predicate = QDiagnostic.diagnostic.expedient.ID.eq(expedient)
							.and(QDiagnostic.diagnostic.entorn.ID.eq(entorn));

		
	    return diagnosticRepository.findAll(predicate);
	  }
	
	
	@RequestMapping(value = "/diagnostic/{expedient}/{entorn}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta/modificació d'un diagnóstic", notes = "")
	  public Diagnostic putDiagnostic(@PathVariable Long expedient,@PathVariable Long entorn,@RequestBody Diagnostic diagnostic) {
		
		Expedient exp = expedientRepository.findOne(expedient);
		
		Entorn ent = entornRepository.findOne(entorn);
	    
		diagnostic.setExpedient(exp);
		diagnostic.setEntorn(ent);
		
		Evaluation.eval(diagnostic, env);
		
		
		Diagnostic newDiagnostic = diagnosticRepository.save(diagnostic);
		
		return newDiagnostic;
	  }
	
}
