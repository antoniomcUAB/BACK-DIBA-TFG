package es.in2.dsdibaapi.controller;

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

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.PreguntaService;
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
	
	
	
	@RequestMapping(value = "/pregunta/{diagnostic}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta/modificació d'una pregunta", notes = "")
	  public Pregunta putPregunta(@PathVariable Long diagnostic,@RequestBody Pregunta pregunta) {
		return preguntaService.save(pregunta,diagnostic);
	  }
	
	@RequestMapping(value = "/diagnostic/{diagnostic}/situacio/{situacio}", method = RequestMethod.DELETE)  
	@ApiOperation(value = "Donar de baixa les preguntes d'una situacio social determinada", notes = "")
	  public void deletePreguntes(@PathVariable Long diagnostic, @PathVariable Long situacio) {
		preguntaService.deleteByDiagnosticSituacioSocial(diagnostic, situacio);
	  }
	 
	
	@RequestMapping(value = "/pregunta/economia/{diagnostic}", method = RequestMethod.PUT)
	@ApiOperation(value = "Alta de factors económics", notes = "")
	  public Pregunta putPreguntaEconomia(@PathVariable Long diagnostic,@RequestBody Set<FactorEconomic> factors) {
		
		Diagnostic diag = diagnosticService.findById(diagnostic);
		
		if (diag.getVersioModel().getLlistaPE().size() > 1) {
		
			Long numFactors = factors.stream().filter(
					p -> !p.getDescripcio().toLowerCase().contains("Existeix una xarxa familia".toLowerCase())
					 || !p.getDescripcio().toLowerCase().contains("Les dificultats en".toLowerCase())).count();
			
			if (numFactors > 6) {
				return addPreguntaEconomica(diag,
						Long.valueOf(diag.getVersioModel().getLlistaPE().get(0)),factors);
			} else {
				return addPreguntaEconomica(diag,
						Long.valueOf(diag.getVersioModel().getLlistaPE().get(1)),factors);
			}
		} else {
			return addPreguntaEconomica(diag,
					Long.valueOf(diag.getVersioModel().getPreguntaEconomica()),factors);
		}
		
	  }
	
	private Pregunta addPreguntaEconomica (Diagnostic diagnostic, Long pregunta, Set<FactorEconomic> factors) {
		
		Pregunta p = preguntaService.findByDiagnosticSituacioSocial(
				diagnostic.getId(),pregunta);
		
		SituacioSocial situacioSocial = situacioSocialService.findById(pregunta);
		
		if (p == null) {
			p = preguntaService.save(Pregunta.builder().factorEconomic(factors).situacioSocial(situacioSocial).unitatFamiliar(false).build(),diagnostic.getId());
		}
		else {
			p.setFactorEconomic(factors);
			p = preguntaService.save(p,diagnostic.getId());
		}
		
		return preguntaService.findById(p.getId());
	}
}
