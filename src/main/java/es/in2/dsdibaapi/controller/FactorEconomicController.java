package es.in2.dsdibaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.service.FactorEconomicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Factor Economic")
public class FactorEconomicController extends BaseController {
	
	@Autowired
	private FactorEconomicService factorEconomicService;
		
	@RequestMapping(value = "/economic/", method = RequestMethod.GET)
	@ApiOperation(value = "Llista de factors econòmics", notes = "")
	  public List<FactorEconomic> getFactorEconomic() {
	     
		return factorEconomicService.findAll();
	  }
	
	
}
