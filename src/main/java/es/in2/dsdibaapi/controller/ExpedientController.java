package es.in2.dsdibaapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.ExpedientGlobal;
import es.in2.dsdibaapi.service.ExpedientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servei Expedient")
public class ExpedientController extends BaseController {
		
	@Autowired
	private ExpedientService expedientService;
	
	@RequestMapping(value={"/expedient/{versio}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
	  @ApiOperation(value="Consulta/modificació d'un expedient", notes="")
	  public Expedient putExpedient(@RequestBody Expedient expedient, @PathVariable Long versio)
	  {
	    return this.expedientService.save(expedient, versio);
	  }
	
	
	  
	  @RequestMapping(value={"/expedient/{municipi}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ApiOperation(value="Consulta de expedients d'un municipi", notes="")
	  public List<ExpedientGlobal> getExpedientMunicipi(@PathVariable Long municipi)
	  {
		  
		  List<ExpedientGlobal> resultat = new ArrayList<ExpedientGlobal> ();
	    try
	    {
	    	
	    	Iterable<Expedient> expedients = this.expedientService.findByMunicipi(municipi);
	    	ExpedientGlobal current = null;
	    	
	    	for (Expedient e:expedients) {
	    		if (current == null) {
	    			
	    			current = ExpedientGlobal.builder()
	    							.expedient(e.getExpedient())
	    							.estat(ExpedientGlobal.Estat.COMPLET)
	    							.professional(e.getProfessional())
	    							.dataCreacio(e.getDATA()).build();
	    			
	    			resultat.add(current);
	    		}
	    		else if (!current.getExpedient().equalsIgnoreCase(e.getExpedient())) {	    			
	    			   			
	    			current = ExpedientGlobal.builder()
							.expedient(e.getExpedient())
							.professional(e.getProfessional())
							.dataCreacio(e.getDATA()).build();
	    			
	    			resultat.add(current);
	    		}
	    		
	    		if (e.getValoracio() != null 
	    				&& ExpedientService.Estat.VALIDAT.toString().equals(e.getEstat())
	    				&& ExpedientGlobal.Estat.COMPLET.equals(current.getEstat())) {
	    			current.setDataValidacio(e.getValoracio().getData());
	    		} 
	    		else {
	    			current.setEstat(ExpedientGlobal.Estat.INCOMPLET);
	    			
	    			if (e.getValoracio() != null) {
	    				current.setDataValidacio(e.getValoracio().getData());
	    			}
	    		}	    		
	    	}	      
	    }
	    catch (NoSuchElementException ex)
	    {
	      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), municipi), ex);
	    }
	    
	    return resultat;
	  }
}
