package es.in2.dsdibaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.TipusPersona;
import es.in2.dsdibaapi.service.TipusPersonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Tipus Persona")
public class TipusPersonaController extends BaseController {
	
	@Autowired
	private TipusPersonaService tipusPersonaService;
		
	@RequestMapping(value = "/tipusPersona/", method = RequestMethod.GET)
	@ApiOperation(value = "LLista de tipusPersonas", notes = "")
	  public List<TipusPersona> getTipusPersona() {
	     
		return tipusPersonaService.findAll();
	  }
	
	
}
