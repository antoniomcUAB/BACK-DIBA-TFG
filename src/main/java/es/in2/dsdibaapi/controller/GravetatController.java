package es.in2.dsdibaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Gravetat;
import es.in2.dsdibaapi.service.GravetatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Gravetat")
public class GravetatController {
	
	@Autowired
	private GravetatService gravetatService;
		
	@RequestMapping(value = "/gravetat/", method = RequestMethod.GET)
	@ApiOperation(value = "Llista de gravetats", notes = "")
	  public List<Gravetat> getGravetat() {
	     
		return gravetatService.findAll();
	  }
	
	
}
