package es.in2.dsdibaapi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.QExpedient;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;

@Service
public class ExpedientServiceImpl implements ExpedientService {
	
	@Autowired
	ExpedientServiceImpl() {}
	
	@Autowired
	ExpedientRepository expedientRepository;
	
	@Autowired
	EstatService estatService;
	
	public Expedient findById(Long id) {
		return expedientRepository.findById(id).get();
    }
	
	public List<Expedient> findAll() {
		return expedientRepository.findAll();
    }
	
	public Expedient save(Expedient expedient) {
		
		for (Diagnostic d:expedient.getDiagnostic()) {
			if (d.getExpedient() == null) {
				d.setExpedient(expedient);
			}
		}
		
		return expedientRepository.save(expedient);
    }
	
	@Transactional
	public Iterable<Expedient> findByMunicipi(Long municipi) {
		Predicate predicate = QExpedient.expedient.professional.municipi.id.eq(municipi);
		
		return expedientRepository.findAll(predicate,new Sort(Sort.Direction.ASC, "codi", "dataCreacio"));
	
    }
	
	public void eval (Expedient e) {
		Boolean validat = true;
		
		for (Diagnostic d:e.getDiagnostic()) {
			if (d.getValoracio() == null 
					|| DiagnosticService.Estat.BORRADOR.toString().equalsIgnoreCase(d.getEstat().getDescripcio())) {
				validat = false;
				break;
			}
		}
		
		if (validat) {
			e.setEstat(estatService.findByDescripcio(Estat.COMPLET.toString()));
		} else {
			e.setEstat(estatService.findByDescripcio(Estat.INCOMPLET.toString()));
		}
		
		expedientRepository.save(e);
	}
	
}
