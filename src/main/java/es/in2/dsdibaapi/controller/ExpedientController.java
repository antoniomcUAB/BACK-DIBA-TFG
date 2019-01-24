package es.in2.dsdibaapi.controller;

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

import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.service.ExpedientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Servei Expedient")
public class ExpedientController
  extends BaseController
{
  private static final Logger log = LoggerFactory.getLogger(ExpedientController.class);
  @Autowired
  private ExpedientService expedientService;
  
  @RequestMapping(value={"/expedient/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Consulta d'un expedient", notes="")
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
  
  @RequestMapping(value={"/expedient/llista/{expedient}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Consulta de expedients", notes="")
  public Iterable<Expedient> getExpedient(@PathVariable String expedient)
  {
    try
    {
      return this.expedientService.findByExpedient(expedient);
    }
    catch (NoSuchElementException ex)
    {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), expedient), ex);
    }
  }
  
  @RequestMapping(value={"/expedient/municipi/{municipi}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Consulta de expedients d'un municipi", notes="")
  public Iterable<Expedient> getExpedientMunicipi(@PathVariable Long municipi)
  {
    try
    {
      return this.expedientService.findByMunicipi(municipi);
    }
    catch (NoSuchElementException ex)
    {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), municipi), ex);
    }
  }
  
  @RequestMapping(value={"/expedient/valorar/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Valoraci� d'un expedient", notes="")
  public Expedient getValoracio(@PathVariable Long id)
  {
    return this.expedientService.avaluar(id);
  }
  
  @RequestMapping(value={"/expedient/{versio}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
  @ApiOperation(value="Consulta/modificaci� d'un expedient", notes="")
  public Expedient putExpedient(@RequestBody Expedient expedient, @PathVariable Long versio)
  {
    return this.expedientService.save(expedient, versio);
  }
  
  @RequestMapping(value={"/expedient/"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
  @ApiOperation(value="Consulta/modificaci� d'un expedient", notes="")
  public Expedient putExpedient(@RequestBody Expedient expedient)
  {
    return this.expedientService.save(expedient);
  }
}
