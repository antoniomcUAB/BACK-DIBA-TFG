package es.in2.dsdibaapi.controller;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.ProfessionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servei Expedient")
public class ExpedientController extends BaseController {
		
	@Autowired
	private ExpedientService expedientService;
	
	@Autowired
	private ProfessionalService professionalService;
	
	
	@Autowired
	private EstatService estatService;
	
	@RequestMapping(value={"/expedient"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
	  @ApiOperation(value="Alta/Modificació d'un expedient", notes="")
	  public Expedient modExpedient(@RequestBody Expedient expedient)
	  {
		expedient.setDataCreacio(new Date());
		
		if (expedient.getEstat() == null) {
			expedient.setEstat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()));
		}
	    return this.expedientService.save(expedient);
	  }
	
	/*
	@RequestMapping(value={"/expedient/{professional}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
	  @ApiOperation(value="Alta d'un expedient", notes="")
	  public Expedient modExpedient(@RequestBody Expedient expedient,@PathVariable Long professional)
	  {
		
		expedient.setDataCreacio(new Date());
		expedient.setEstat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()));
		
		Professional p = professionalService.findById(professional);
		
		expedient.setProfessional(p);
		
		
		
		return expedientService.save(expedient);
	  }
	
	*/
	
	  
	  @RequestMapping(value={"/expedient/llista/{municipi}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ApiOperation(value="Consulta d'expedients d'un municipi", notes="")
	  public Iterable<Expedient> getExpedientMunicipi(@PathVariable Long municipi)
	  {
		  
		  Iterable<Expedient> resultat = null;
	    try
	    {
	    	resultat = this.expedientService.findByMunicipi(municipi);
	    	
	    }
	    catch (NoSuchElementException ex)
	    {
	      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), municipi), ex);
	    }
	    
	    return resultat;
	  }
	  
	  
	  
	  @RequestMapping(value={"/expedient/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ApiOperation(value="Consulta d'un expedient", notes="")
	  public Expedient getExpedient(@PathVariable Long id)
	  {
		  
		  Expedient resultat = null;
	    try
	    {
	    	resultat = this.expedientService.findById(id);
	    	
	    }
	    catch (NoSuchElementException ex)
	    {
	      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), id), ex);
	    }
	    
	    return resultat;
	  }
}
