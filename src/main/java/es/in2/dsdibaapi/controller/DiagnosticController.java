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

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.DiagnosticService.Estat;
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.VersioModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Servei Diagnostic")
public class DiagnosticController
  extends BaseController
{
  private static final Logger log = LoggerFactory.getLogger(DiagnosticController.class);
  @Autowired
  private DiagnosticService diagnosticService;
  
  @Autowired
  private ExpedientService expedientService;
  
  @Autowired
	private EstatService estatService;	

  @Autowired
  private VersioModelService versioModelService;
  
  
  @RequestMapping(value={"/diagnostic/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Consulta d'un diagnòstic", notes="")
  public Diagnostic getExpedient(@PathVariable Long id)
  {
    try
    {
      return this.diagnosticService.findById(id);
    }
    catch (NoSuchElementException ex)
    {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), id), ex);
    }
  }
  
  
  
  @RequestMapping(value={"/diagnostic/llista/{expedient}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Diagnostic d'un expedient", notes="")
  public Iterable<Diagnostic> getExpedient(@PathVariable String expedient)
  {
    try
    {    	
      return this.diagnosticService.findByExpedient(UriUtils.decode(expedient, "UTF-8"));
    }
    catch (NoSuchElementException ex)
    {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, getErrorNotFound(getClass(), expedient), ex);
    }
  } 
  
  
  @RequestMapping(value={"/diagnostic/valorar/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ApiOperation(value="Valoració d'un diagnòstic", notes="")
  public Diagnostic getValoracio(@PathVariable Long id)
  {
    return this.diagnosticService.avaluar(id);
  }
  
  
  @RequestMapping(value={"/expedient/{expedient}/diagnostic/{versio}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
  @ApiOperation(value="Nou diagnòstic", notes="")
  public Diagnostic putExpedient(@RequestBody Diagnostic diagnostic,@PathVariable Long expedient, @PathVariable Long versio)  
  {
	  diagnostic.setData(new Date ());
	  VersioModel v = versioModelService.findById(versio);
		
	  diagnostic.setVersioModel(v);
	  diagnostic.setEstat(estatService.findByDescripcio(Estat.BORRADOR.toString()));
	  Expedient e = expedientService.findById(expedient);
	  
	  e.getDiagnostic().add(diagnostic);
	  
    return this.expedientService.save(e).getDiagnostic().get(this.expedientService.save(e).getDiagnostic().size()-1);
  }
  
  
  
  @RequestMapping(value={"/expedient/{expedient}/avaluar/diagnostic"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
  @ApiOperation(value="Modificació d'un diagnòstic", notes="")
  public Diagnostic putDiagnosticAvaluar(@RequestBody Diagnostic diagnostic,@PathVariable Long expedient)
  {
	  
	  Expedient e = expedientService.findById(expedient);
	  
	  diagnostic.setExpedient(e);
	  
	  
	  diagnostic = this.diagnosticService.save(diagnostic);
	  return diagnosticService.avaluar(diagnostic.getId());
  }
  
  @RequestMapping(value={"/expedient/{expedient}/diagnostic"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
  @ApiOperation(value="Modificació d'un diagnòstic", notes="")
  public Diagnostic putDiagnostic(@RequestBody Diagnostic diagnostic,@PathVariable Long expedient)
  {
	  
	  Expedient e = expedientService.findById(expedient);
	  
	  diagnostic.setExpedient(e);
	  
	  
    return this.diagnosticService.save(diagnostic);
  }
}
