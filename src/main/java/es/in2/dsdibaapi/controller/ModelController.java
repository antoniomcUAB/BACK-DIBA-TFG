package es.in2.dsdibaapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Model;
import es.in2.dsdibaapi.model.QAmbit;
import es.in2.dsdibaapi.model.QFactorEconomic;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.AmbitRepository;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;
import es.in2.dsdibaapi.repository.VersioModelRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servei Model")
public class ModelController {
	
	@Autowired
	private AmbitRepository ambitRepository;
	
	@Autowired
	private VersioModelRepository versionRepository;
	
	@Autowired
	private FactorEconomicRepository factorEconomicRepository;
	
	
	
	@RequestMapping(value = {"/model/{versio}", "/model"}, method = RequestMethod.GET)
	@ApiOperation(value = "Consulta del model", notes = "")
	  public Model getModel(@PathVariable(required=false) Optional<Long> versio) {
		
		Predicate predicateAmbit = null;
		Predicate predicateFactor = null;
		
		if (versio.isPresent()) {
			predicateAmbit = QAmbit.ambit.entorn.any().situacioSocial.any().versioModel.ID.eq(versio.get());
		} else {
			List<VersioModel> versions = versionRepository.findAll(new Sort(Sort.Direction.DESC, "ID"));
			predicateAmbit = QAmbit.ambit.entorn.any().situacioSocial.any().versioModel.ID.eq(versions.get(0).getID());
		}
		
		if (versio.isPresent()) {
			predicateFactor = QFactorEconomic.factorEconomic.versioModel.ID.eq(versio.get());
		} else {
			List<VersioModel> versions = versionRepository.findAll(new Sort(Sort.Direction.DESC, "ID"));
			predicateFactor = QFactorEconomic.factorEconomic.versioModel.ID.eq(versions.get(0).getID());
		}
		
	    Model model = new Model();
	    model.setAmbits(ambitRepository.findAll(predicateAmbit));
	    model.setFactorEconomic(factorEconomicRepository.findAll(predicateFactor));  
	    
	    
	    return model;
	  }

}
