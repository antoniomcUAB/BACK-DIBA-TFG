package es.in2.dsdibaapi.controller;

import java.util.Date;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriUtils;

import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.service.ExpedientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Servei Diagnostic")
public class DiagnosticController
  extends BaseController
{
  private static final Logger log = LoggerFactory.getLogger(DiagnosticController.class);
  @Autowired
  private ExpedientService expedientService;
  
  /*
  @RequestMapping(value={"/diagnostic/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Consulta d'un diagnòstic", notes="")
  public Expedient getExpedient(@PathVariable Long id)
  {
    try
    {
      return this.expedientService.findById(id);
    }
    catch (NoSuchElementException ex)
    {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), id), ex);
    }
  }
  */
  
  
  @RequestMapping(value={"/diagnostic/llista/{expedient}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Consulta de diagnòstic", notes="")
  public Iterable<Expedient> getExpedient(@PathVariable String expedient)
  {
    try
    {    	
      return this.expedientService.findByExpedient(UriUtils.decode(expedient, "UTF-8"));
    }
    catch (NoSuchElementException ex)
    {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), expedient), ex);
    }
  } 
  
  
  @RequestMapping(value={"/diagnostic/valorar/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Valoració d'un diagnòstic", notes="")
  public Expedient getValoracio(@PathVariable Long id)
  {
    return this.expedientService.avaluar(id);
  }
  
  @RequestMapping(value={"/diagnostic/{versio}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
  @ApiOperation(value="Nou diagnòstic", notes="")
  public Expedient putExpedient(@RequestBody Expedient expedient, @PathVariable Long versio)  
  {
	  expedient.setDATA(new Date ());
    return this.expedientService.save(expedient, versio);
  }
  
  
  
  @RequestMapping(value={"/diagnostic/"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
  @ApiOperation(value="Modificació d'un diagnòstic", notes="")
  public Expedient putExpedient(@RequestBody Expedient expedient)
  {
    return this.expedientService.save(expedient);
  }
}
