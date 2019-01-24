package es.in2.dsdibaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.service.FrequenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Frequència")
public class FrequenciaController extends BaseController {
	
	@Autowired
	private FrequenciaService frequenciaService;
		
	@RequestMapping(value = "/frequencia/", method = RequestMethod.GET)
	@ApiOperation(value = "Llista de frequències", notes = "")
	  public List<Frequencia> getFrequencia() {
	     
		return frequenciaService.findAll();
	  }
	
	
}
