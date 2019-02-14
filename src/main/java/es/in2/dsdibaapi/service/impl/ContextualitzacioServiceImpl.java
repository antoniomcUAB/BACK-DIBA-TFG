package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.QContextualitzacio;
import es.in2.dsdibaapi.repository.ContextualitzacioRepository;
import es.in2.dsdibaapi.service.ContextualitzacioService;
import es.in2.dsdibaapi.service.DiagnosticService;
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
	private DiagnosticService expedientService;
	
	@Cacheable("context")
    public Contextualitzacio findById(Long id) {
		return contextualitzacioRepository.findById(id).get();
    }

	
    public Iterable<Contextualitzacio> findByDiagnosticAmbit(Long diagnostic,Long ambit) {
		
		Predicate predicate = QContextualitzacio.contextualitzacio.factor.ambit.id.eq(ambit)
						.and(QContextualitzacio.contextualitzacio.diagnostic.id.eq(diagnostic));
		
		return contextualitzacioRepository.findAll(predicate);
    }
	
	public Contextualitzacio save (Contextualitzacio contextualitzacio, Long diagnostic, Long factor) {
		Factor f = factorService.findById(factor);
		Diagnostic e = expedientService.findById(diagnostic);
		
		contextualitzacio.setDiagnostic(e);
		contextualitzacio.setFactor(f);
		
		return saveContext(contextualitzacio);
	}
	
	 public void delete(Long id) {
		 contextualitzacioRepository.deleteById(id);
	    }
	
	public Contextualitzacio save (Contextualitzacio contextualitzacio) {
		
		return saveContext(contextualitzacio);
	}
	
	private Contextualitzacio saveContext (Contextualitzacio contextualitzacio) {
		return contextualitzacioRepository.save(contextualitzacio);
	}

}

