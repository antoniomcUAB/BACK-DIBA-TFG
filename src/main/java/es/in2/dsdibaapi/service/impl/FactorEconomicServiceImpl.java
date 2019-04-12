package es.in2.dsdibaapi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.QFactorEconomic;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;
import es.in2.dsdibaapi.service.FactorEconomicService;

@Service
public class FactorEconomicServiceImpl implements FactorEconomicService {
	
	@Autowired
	FactorEconomicServiceImpl() {}
	
	@Autowired
	FactorEconomicRepository factorRepository;
	
	@Cacheable("factor_economic")
    public FactorEconomic findById(Long id) {
		return factorRepository.findById(id).get();
    }
	
	@Cacheable("factor_economic_all")
    public Iterable<FactorEconomic> findAll(Optional<Long> versio) {
		if (versio.isPresent()) {
			
			Predicate predicate = null;
			
			predicate = QFactorEconomic.factorEconomic.versioModel.id.eq(versio.get());
			
			return factorRepository.findAll(predicate);

		}
		
		return factorRepository.findAll();
    }
	
	public FactorEconomic save(FactorEconomic factor) {
		return factorRepository.save(factor);
    }
	
	public void deleteById(Long id) {
		factorRepository.deleteById(id);
    }
	

}
