package es.in2.dsdibaapi.controller;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servei Expedient")
public class ExpedientController extends BaseController {
		
	@Autowired
	private ExpedientService expedientService;
	
	
	@Autowired
	private EstatService estatService;
	
	@PutMapping (value={"/expedient"})
	  @ApiOperation(value="Alta/Modificació d'un expedient", notes="")
	  public Expedient modExpedient(@RequestBody Expedient expedient)
	  {
		expedient.setDataCreacio(new Date());
		
		if (expedient.getEstat() == null) {
			expedient.setEstat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()));
		}
	    return this.expedientService.save(expedient);
	  }
	
	
	  
	  @GetMapping (value={"/expedient/llista/{municipi}"})
	  @ApiOperation(value="Consulta d'expedients d'un municipi", notes="")
	  public Page<Expedient> getExpedientMunicipi(@RequestParam("page") Optional<Integer> page, 
			  @RequestParam("size") Optional<Integer> size, @PathVariable Long municipi,
			  @RequestParam Optional<String> sort,
			  @RequestParam(required=false) Optional<String> codi, 
			  @RequestParam(required=false) Optional<String> nomComplet, 
			  @RequestParam(required=false) Optional<String> estat,
			  @RequestParam(required=false) Optional<String> dataCreacio,
			  @RequestParam(required=false) Optional<String> dataValidacio)
	  {
		  
		  Page<Expedient> resultat = null;
	    try
	    {
	    	resultat = this.expedientService.findByMunicipi(page.orElse(0), 
	    									size.orElse(0), 
	    									municipi, 
	    									codi.orElse(null), 
	    									nomComplet.orElse(null), 
	    									estat.orElse(null), 
	    									sort.orElse(null),
	    									dataCreacio.orElse(null),
	    									dataValidacio.orElse(null));
	    	
	    	
	    }
	    catch (NoSuchElementException ex)
	    {
	      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), municipi), ex);
	    }
	    
	    return resultat;
	  }
	  
	  
	  
	  @GetMapping(value={"/expedient/{id}"})
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
