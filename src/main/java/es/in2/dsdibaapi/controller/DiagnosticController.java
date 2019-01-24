package es.in2.dsdibaapi.controller;

import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Economia;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EconomiaService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.SituacioSocialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Diagnostic")
public class DiagnosticController extends BaseController {
	
	@Autowired
	private DiagnosticService diagnosticService;
	
	@Autowired
	private EconomiaService economiaService;
	
	@Autowired
	private ExpedientService expedientService;
	
	@Autowired
	private SituacioSocialService situacioSocialService;
	
	@RequestMapping(value = "/diagnostic/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta d'un diagnóstic", notes = "")
	  public Diagnostic getDiagnostic(@PathVariable Long id) {
	     try {
	    	 return diagnosticService.findById(id);
	     } catch (NoSuchElementException ex) {
				throw new ResponseStatusException(
				          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),id), ex);
			}
	  }

	
	@RequestMapping(value = {"/diagnostics/{expedient}", "/diagnostics/{expedient}/{entorn}"}, method = RequestMethod.GET)
	@ApiOperation(value = "Diagnóstics per entorn", notes = "")
	  public Iterable<Diagnostic> getDiagnostic(@PathVariable Long expedient,@PathVariable (required=false) Long entorn) {
		try {
			return diagnosticService.findByExpedientEntorn(expedient,entorn);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),expedient), ex);
		}
	  }
	
	
	@RequestMapping(value = "/diagnostic/{expedient}/{entorn}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta/modificació d'un diagnóstic", notes = "")
	  public Diagnostic putDiagnostic(@PathVariable Long expedient,@PathVariable Long entorn,@RequestBody Diagnostic diagnostic) {
		return diagnosticService.save(diagnostic,expedient,entorn);
	  }
	
	@RequestMapping(value = "/diagnostic/economia/{expedient}/{factors}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta de factors económics", notes = "")
	  public Diagnostic putDiagnosticEconomia(@PathVariable Long expedient,@PathVariable Long entorn,@RequestBody Set<FactorEconomic> factors) {
		
		
		Expedient exp = expedientService.findById(expedient);
		
		Diagnostic diagnostic = diagnosticService.findByExpedientSituacioSocial(exp.getID(),exp.getDiagnosticEconomic());
		
		SituacioSocial situacioSocial = situacioSocialService.findById(exp.getDiagnosticEconomic());
		
		if (diagnostic == null) {
			diagnostic = diagnosticService.save(Diagnostic.builder().factorEconomic(factors).situacioSocial(situacioSocial).build(),expedient,entorn);
		}
		else {
			diagnostic.setFactorEconomic(factors);
			diagnostic = diagnosticService.save(diagnostic,expedient,entorn);
		}
		
		return diagnosticService.findById(diagnostic.getID());
	  }
	
	
}
