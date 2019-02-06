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

import es.in2.dsdibaapi.model.Avaluacio;
import es.in2.dsdibaapi.model.Valoracio;
import es.in2.dsdibaapi.service.AvaluacioService;
import es.in2.dsdibaapi.service.ValoracioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Avaluacio")
public class AvaluacioController extends BaseController {
	
	@Autowired
	private AvaluacioService avaluacioService;
	
	@Autowired
	private ValoracioService valoracioService;
	
	/*
	@RequestMapping(value = "/avaluacio/{valoracio}", method = RequestMethod.PUT)
	@ApiOperation(value = "Modificació d'una avaluacio", notes = "")
	  public Avaluacio putAvaluacio(@PathVariable Long valoracio, @RequestBody Avaluacio avaluacio) {
		try {
			Valoracio v = valoracioService.findById(valoracio);
			
			avaluacio.setValoracio(v);
			
			return avaluacioService.save(avaluacio);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),valoracio), ex);
		}
	  }
	*/
	
}
