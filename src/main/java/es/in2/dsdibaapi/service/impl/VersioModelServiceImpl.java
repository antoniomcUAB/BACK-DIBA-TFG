package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.VersioModelRepository;
import es.in2.dsdibaapi.service.VersioModelService;

@Service
public class VersioModelServiceImpl implements VersioModelService{
	
	@Autowired
	VersioModelServiceImpl() {}
	
	@Autowired
	VersioModelRepository versioRepository;
	
	@Cacheable("versio")
    public VersioModel findById(Long id) {
		return versioRepository.findById(id).get();
    }
	
	@Cacheable("versio")
    public VersioModel findCurrent() {		
		return versioRepository.findAll(new Sort(Sort.Direction.DESC, "id")).get(0);
    }
	
	@Cacheable("versions")
    public List<VersioModel> findAll() {
		return versioRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }
	

	public VersioModel save(VersioModel v) {
		return versioRepository.save(v);
    }
	

}
