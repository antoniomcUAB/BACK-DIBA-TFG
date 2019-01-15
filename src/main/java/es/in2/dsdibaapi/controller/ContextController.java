package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.QContextualitzacio;
import es.in2.dsdibaapi.repository.ContextualitzacioRepository;
import es.in2.dsdibaapi.repository.EntornRepository;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.repository.FactorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Factors de Context")
public class ContextController {
	
	@Autowired
	private ContextualitzacioRepository contextualitzacioRepository;
	
	@Autowired
	private ExpedientRepository expedientRepository;
	
	@Autowired
	private EntornRepository entornRepository;
	
	@Autowired
	private FactorRepository factorRepository;
	
	
	@RequestMapping(value = "/context/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta d'un factor de context", notes = "")
	  public Contextualitzacio getContext(@PathVariable Long id) {
	     
		Contextualitzacio contextualitzacio = contextualitzacioRepository.findById(id).get();		
	    
	    return contextualitzacio;
	  }

	
	@RequestMapping(value = "/context/{expedient}/{entorn}", method = RequestMethod.GET)
	@ApiOperation(value = "Factors de context per entorn", notes = "")
	  public Iterable<Contextualitzacio> getContext(@PathVariable Long expedient,@PathVariable Long entorn) {
		
		Expedient exp = expedientRepository.findById(expedient).get();
		
		Entorn ent = entornRepository.findById(entorn).get();
		
		Predicate predicate = QContextualitzacio.contextualitzacio.factor.entorn.ID.eq(entorn)
						.and(QContextualitzacio.contextualitzacio.expedient.ID.eq(expedient));
		
		Iterable<Contextualitzacio> contextualitzacions = contextualitzacioRepository.findAll(predicate);
		
		
	    
	    return contextualitzacions;
	  }
	
	
	@RequestMapping(value = "/context/{expedient}/{factor}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta/modificació d'un factor de context", notes = "")
	  public Contextualitzacio putContext(@PathVariable Long expedient,@PathVariable Long factor,@RequestBody Contextualitzacio contextualitzacio) {
		
		Expedient exp = expedientRepository.findById(expedient).get();
		
		Factor f = factorRepository.findById(factor).get();
	    
		contextualitzacio.setExpedient(exp);
		contextualitzacio.setFactor(f);
		
		Contextualitzacio newContextualitzacio = contextualitzacioRepository.save(contextualitzacio);
		
		return newContextualitzacio;
	  }
	
}
