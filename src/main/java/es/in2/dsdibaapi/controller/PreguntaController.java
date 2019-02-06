package es.in2.dsdibaapi.controller;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.model.Economia;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.service.PreguntaService;
import es.in2.dsdibaapi.service.EconomiaService;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.SituacioSocialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Pregunta")
public class PreguntaController extends BaseController {
	
	@Autowired
	private PreguntaService preguntaService;
	
	@Autowired
	private EconomiaService economiaService;
	
	@Autowired
	private DiagnosticService diagnosticService;
	
	@Autowired
	private SituacioSocialService situacioSocialService;
	
	@RequestMapping(value = "/pregunta/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Esborrar una pregunta", notes = "")
	  public void deletePregunta(@PathVariable Long id) {		
	     preguntaService.delete(id);
	  }
	
	@RequestMapping(value = "/pregunta/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta d'una preguntes", notes = "")
	  public Pregunta getDiagnostic(@PathVariable Long id) {
	     try {
	    	 return preguntaService.findById(id);
	     } catch (NoSuchElementException ex) {
				throw new ResponseStatusException(
				          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),id), ex);
			}
	  }

	
	@RequestMapping(value = {"/preguntas/{diagnostic}", "/preguntas/{diagnostic}/{entorn}"}, method = RequestMethod.GET)
	@ApiOperation(value = "Preguntes per entorn", notes = "")
	  public Iterable<Pregunta> getDiagnostic(@PathVariable Long diagnostic,@PathVariable (required=false) Long entorn) {
		try {
			return preguntaService.findByDiagnosticEntorn(diagnostic,entorn);
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),diagnostic), ex);
		}
	  }
	
	/*@RequestMapping(value={"/diagnostic/economic/{expedient}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
	  @ApiOperation(value="Consulta/modificaci� d'un expedient", notes="")
	  public Expedient putExpedient(@RequestBody List<FactorEconomic> factors, @PathVariable Long expedient)
	  {
	    return this.expedientService.save(expedient, versio);
	  }*/
	
	
	@RequestMapping(value = "/pregunta/{diagnostic}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta/modificació d'una pregunta", notes = "")
	  public Pregunta putPregunta(@PathVariable Long diagnostic,@RequestBody Pregunta pregunta) {
		return preguntaService.save(pregunta,diagnostic);
	  }
	
	@RequestMapping(value = "/pregunta/economia/{diagnostic}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta de factors económics", notes = "")
	  public Pregunta putPreguntaEconomia(@PathVariable Long diagnostic,@RequestBody Set<FactorEconomic> factors) {
		
		
		Diagnostic exp = diagnosticService.findById(diagnostic);
		
		Pregunta d = preguntaService.findByDiagnosticSituacioSocial(
								exp.getId(),exp.getVersioModel().getPreguntaEconomica());
		
		SituacioSocial situacioSocial = situacioSocialService.findById(exp.getVersioModel().getPreguntaEconomica());
		
		if (d == null) {
			d = preguntaService.save(Pregunta.builder().factorEconomic(factors).situacioSocial(situacioSocial).build(),diagnostic);
		}
		else {
			d.setFactorEconomic(factors);
			d = preguntaService.save(d,diagnostic);
		}
		
		return preguntaService.findById(d.getId());
	  }
	
	
}
