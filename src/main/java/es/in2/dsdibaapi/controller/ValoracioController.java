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

import es.in2.dsdibaapi.model.Valoracio;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.ValoracioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Valoracio")
public class ValoracioController extends BaseController {
	
	@Autowired
	private ValoracioService valoracioService;
	
	@Autowired
	private ExpedientService expedientService;
	
	
	
	@RequestMapping(value = "/valoracio/{expedient}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta de la valoracio d'un expedient", notes = "")
	  public Valoracio getValoracio(@PathVariable Long expedient) {
		try {
			return expedientService.findById(expedient).getValoracio();
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),expedient), ex);
		}
	  }

	
	
	@RequestMapping(value = "/valoracio", method = RequestMethod.PUT)
	@ApiOperation(value = "Modificació d'una valoracio", notes = "")
	  public Valoracio putValoracio(@RequestBody Valoracio valoracio) {
		return valoracioService.save(valoracio);
	  }
	
	
}
