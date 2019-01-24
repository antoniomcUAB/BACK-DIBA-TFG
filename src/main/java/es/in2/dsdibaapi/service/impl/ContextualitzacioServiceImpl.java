package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.QContextualitzacio;
import es.in2.dsdibaapi.repository.ContextualitzacioRepository;
import es.in2.dsdibaapi.service.ContextualitzacioService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.FactorService;

@Service
public class ContextualitzacioServiceImpl implements ContextualitzacioService{
	
	@Autowired
	ContextualitzacioServiceImpl() {}
	
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

	
    public Iterable<Contextualitzacio> findByExpedientAmbit(Long expedient,Long ambit) {
		
		Predicate predicate = QContextualitzacio.contextualitzacio.factor.ambit.ID.eq(ambit)
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

