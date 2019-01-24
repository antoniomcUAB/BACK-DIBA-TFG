package es.in2.dsdibaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.service.PersonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Persona")
public class PersonaController extends BaseController{
	
	@Autowired
	private PersonaService personaService;
		
	@RequestMapping(value = "/persona/", method = RequestMethod.GET)
	@ApiOperation(value = "Llista de persones", notes = "")
	  public List<Persona> getPersona() {
	     
		return personaService.findAll();
	  }
	
	
}
