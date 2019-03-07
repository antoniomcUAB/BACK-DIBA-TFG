package es.in2.dsdibaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.service.ProfessionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Professional")
public class ProfessionalController extends BaseController{
	
	@Autowired
	private ProfessionalService professionalService;
	
	
		
	@RequestMapping(value = "/professional/", method = RequestMethod.GET)
	@ApiOperation(value = "Llista de professionals", notes = "")
	  public List<Professional> getProfessional() {
	     
		return professionalService.findAll();
	  }
	
	@RequestMapping(value = "/professional/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Llista de professionals", notes = "")
	  public Professional getProfessional(@PathVariable Long id) {
	     
		return professionalService.findById(id);
	  }
	
	
	@RequestMapping(value={"/professional/"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
	  @ApiOperation(value="Alta/Modificació d'un professional", notes="")
	  public Professional modExpedient(@RequestBody Professional professional)
	  {
		
	    return professionalService.save(professional);
	  }
	
}
