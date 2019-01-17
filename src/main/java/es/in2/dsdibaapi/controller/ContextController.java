package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.service.ContextualitzacioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Factors de Context")
public class ContextController {
	
	@Autowired
	private ContextualitzacioService contextualitzacioService;
	
	
	
	@RequestMapping(value = "/context/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta d'un factor de context", notes = "")
	  public Contextualitzacio getContext(@PathVariable Long id) {
	     
		return contextualitzacioService.findById(id);
	  }

	
	@RequestMapping(value = "/context/{expedient}/{entorn}", method = RequestMethod.GET)
	@ApiOperation(value = "Factors de context per entorn", notes = "")
	  public Iterable<Contextualitzacio> getContext(@PathVariable Long expedient,@PathVariable Long entorn) {
		return contextualitzacioService.findByExpedientEntorn(expedient, entorn);
	  }
	
	
	@RequestMapping(value = "/context/{expedient}/{factor}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta/modificació d'un factor de context", notes = "")
	  public Contextualitzacio putContext(@PathVariable Long expedient,@PathVariable Long factor,@RequestBody Contextualitzacio contextualitzacio) {
		return contextualitzacioService.save(contextualitzacio, expedient, factor);
	  }
	
}
