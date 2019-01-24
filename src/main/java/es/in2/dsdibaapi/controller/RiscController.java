package es.in2.dsdibaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.service.RiscService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Risc")
public class RiscController extends BaseController {
	
	@Autowired
	private RiscService riscService;
		
	@RequestMapping(value = "/risc/", method = RequestMethod.GET)
	@ApiOperation(value = "LLista de riscs", notes = "")
	  public List<Risc> getRisc() {
	     
		return riscService.findAll();
	  }
	
	
}
