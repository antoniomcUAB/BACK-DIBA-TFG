package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Gravetat;
import es.in2.dsdibaapi.model.QGravetat;
import es.in2.dsdibaapi.repository.GravetatRepository;
import es.in2.dsdibaapi.service.GravetatService;

@Service
public class GravetatServiceImpl implements GravetatService {
	
	@Autowired
	GravetatServiceImpl() {}
	
	@Autowired
	GravetatRepository gravetatRepository;
	
	@Cacheable("gravetat")
    public Gravetat findById(Long id) {
		return gravetatRepository.findById(id).get();
    }
	
	@Cacheable("gravetat")
    public List<Gravetat> findAll() {
		return gravetatRepository.findAll();
    }

	@Cacheable("gravetat")
    public Gravetat findByDescription(String description) {
		
		Predicate predicate = QGravetat.gravetat.descripcio.equalsIgnoreCase(description);
		
		return gravetatRepository.findOne(predicate).get();
    }
	
	public Gravetat save(Gravetat gravetat) {
		return gravetatRepository.save(gravetat);
    }

}
