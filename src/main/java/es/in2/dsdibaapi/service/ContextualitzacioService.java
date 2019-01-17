package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.QContextualitzacio;
import es.in2.dsdibaapi.repository.ContextualitzacioRepository;

@Service
public class ContextualitzacioService {
	
	@Autowired
	ContextualitzacioService() {}
	
	@Autowired
	private ContextualitzacioRepository contextualitzacioRepository;
	
	@Autowired
	private FactorService factorService;
	
	@Autowired
	private ExpedientService expedientService;
	
	@Cacheable("context")
    public Contextualitzacio findById(Long id) {
		return contextualitzacioRepository.findById(id).get();
    }

	
    public Iterable<Contextualitzacio> findByExpedientEntorn(Long expedient,Long entorn) {
		
		Predicate predicate = QContextualitzacio.contextualitzacio.factor.entorn.ID.eq(entorn)
						.and(QContextualitzacio.contextualitzacio.expedient.ID.eq(expedient));
		
		return contextualitzacioRepository.findAll(predicate);
    }
	
	public Contextualitzacio save (Contextualitzacio contextualitzacio, Long expedient, Long factor) {
		Factor f = factorService.findById(factor);
		Expedient e = expedientService.findById(expedient);
		
		contextualitzacio.setExpedient(e);
		contextualitzacio.setFactor(f);
		
		return saveContext(contextualitzacio);
	}
	
	public Contextualitzacio save (Contextualitzacio contextualitzacio) {
		
		return saveContext(contextualitzacio);
	}
	
	private Contextualitzacio saveContext (Contextualitzacio contextualitzacio) {
		return contextualitzacioRepository.save(contextualitzacio);
	}

}

