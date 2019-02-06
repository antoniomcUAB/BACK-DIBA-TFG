package es.in2.dsdibaapi.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.in2.dsdibaapi.model.Model;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.VersioModelRepository;
import es.in2.dsdibaapi.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Servei Model")
public class ModelController extends BaseController {
		
	@Autowired
	private VersioModelRepository versionRepository;
	
	@Autowired
	private ModelService modelService;
	
	
	
	@RequestMapping(value = {"/model/{versio}", "/model"}, method = RequestMethod.GET)
	@ApiOperation(value = "Consulta del model", notes = "")
	  public Model getModel(@PathVariable(required=false) Optional<Long> versio) {
		
		Model model = null;
		
		try {
			if (versio.isPresent()) {
				model = modelService.findByVersion(versio.get());
			} else {
				List<VersioModel> versions = versionRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
				model = modelService.findByVersion(versions.get(0).getId());
			}
		} catch (NoSuchElementException ex) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND,getErrorNotFound(this.getClass(),versio), ex);
		}
		return model;
	  }

}
