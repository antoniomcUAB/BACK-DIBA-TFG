package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.QFactor;
import es.in2.dsdibaapi.repository.FactorRepository;

@Service
public class FactorService {
	
	@Autowired
	FactorService() {}
	
	@Autowired
	FactorRepository factorRepository;
	
	@Cacheable("factor")
    public Factor findById(Long id) {
		return factorRepository.findById(id).get();
    }
	
	public Factor save(Factor factor) {
		return factorRepository.save(factor);
    }
	

	@Cacheable("factor")
    public Factor findByDescription(String description) {
		
		Predicate predicate = QFactor.factor.descripcio.equalsIgnoreCase(description);
		
		return factorRepository.findOne(predicate).get();
    }
	
	

}
