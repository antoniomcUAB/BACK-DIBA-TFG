package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import es.in2.dsdibaapi.service.AvaluacioService;
import es.in2.dsdibaapi.service.ValoracioService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Servei Avaluacio")
public class AvaluacioController extends BaseController {
	
	@Autowired
	private AvaluacioService avaluacioService;
	
	@Autowired
	private ValoracioService valoracioService;
	
	
	
}
