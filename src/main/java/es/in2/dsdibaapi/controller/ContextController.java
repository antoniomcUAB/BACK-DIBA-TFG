package es.in2.dsdibaapi.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.service.ContextualitzacioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

//@RestController
@Slf4j
//@Api(value = "Servei Factors de Context")
public class ContextController extends BaseController {
	/*
	@Autowired
	private ContextualitzacioService contextualitzacioService;
	
	
	
	@RequestMapping(value = "/context/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta d'un factor de context", notes = "")
	  public Contextualitzacio getContext(@PathVariable Long id) {
	     try {
	    	 return contextualitzacioService.findById(id);
	     } catch (NoSuchElementException ex) {
				throw new ResponseStatusException(
				          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),id), ex);
			}
	  }

	
	@RequestMapping(value = "/context/{expedient}/{ambit}", method = RequestMethod.GET)
	@ApiOperation(value = "Factors de context per entorn", notes = "")
	  public Iterable<Contextualitzacio> getContext(@PathVariable Long expedient,@PathVariable Long ambit) {
		try {
			return contextualitzacioService.findByExpedientAmbit(expedient, ambit);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),expedient), ex);
		}
	  }
	
	
	@RequestMapping(value = "/context/{expedient}/{factor}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta/modificació d'un factor de context", notes = "")
	  public Contextualitzacio putContext(@PathVariable Long expedient,@PathVariable Long factor,@RequestBody Contextualitzacio contextualitzacio) {
		return contextualitzacioService.save(contextualitzacio, expedient, factor);
	  }
	*/
	
}
