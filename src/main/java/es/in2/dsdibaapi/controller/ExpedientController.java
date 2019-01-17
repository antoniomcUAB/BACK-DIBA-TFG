package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.service.ExpedientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Expedient")
public class ExpedientController {
	
	
	@Autowired
	private ExpedientService expedientService;
	
	
	
	
	@RequestMapping(value = "/expedient/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta d'un expedient", notes = "")
	  public Expedient getExpedient(@PathVariable Long id) {
	    return expedientService.findById(id);
	  }

	
	
	@RequestMapping(value = "/expedient/{versio}", method = RequestMethod.PUT)
	@ApiOperation(value = "Consulta/modificació d'un expedient", notes = "")
	  public Expedient putExpedient(@RequestBody Expedient expedient,@PathVariable Long versio) {	
		return expedientService.save(expedient, versio);
	  }
	
}
