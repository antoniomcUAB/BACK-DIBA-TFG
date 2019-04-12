package es.in2.dsdibaapi.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.VersioModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei VersioModel")
public class VersioModelController extends BaseController {
	
	@Autowired
	private VersioModelService versioModelService;
	
	
		
	@RequestMapping(value = "/versio/", method = RequestMethod.GET)
	@ApiOperation(value = "LLista de versioModels", notes = "")
	  public List<VersioModel> getVersioModel() {
	     
		return versioModelService.findAll();
	  }
	
	@RequestMapping(value = "/versio/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta de versió", notes = "")
	  public VersioModel getVersio(@PathVariable Long id) {	
		try {
			return versioModelService.findById(id);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),id), ex);
		}
	  }
	
	@RequestMapping(value = "/versio/current/", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta de versió actual", notes = "")
	  public VersioModel getVersio() {
		
		return versioModelService.findCurrent();
	  }
	
	

}
