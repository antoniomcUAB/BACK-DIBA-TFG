package es.in2.dsdibaapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.AmbitDiagnostic;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.QExpedient;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.service.AmbitService;
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
	
	@Autowired
	AmbitService ambitService;
	
	public Expedient findById(Long id) {
		return expedientRepository.findById(id).get();
    }
	
	public List<Expedient> findAll() {
		return expedientRepository.findAll();
    }
	
	public Expedient save(Expedient expedient) {
		
		if (expedient.getDiagnostic() != null) {
			for (Diagnostic d:expedient.getDiagnostic()) {
				if (d.getExpedient() == null) {
					d.setExpedient(expedient);
				}
				
				if (d.getAmbit() == null ||
						d.getAmbit().isEmpty()) {
					d.setAmbit(new ArrayList<AmbitDiagnostic>());
					Iterable<Ambit> ambits = ambitService.findAll(d.getVersioModel().getId());
					
					for (Ambit a:ambits) {				
						d.getAmbit().add(AmbitDiagnostic.builder().ambit(a).build());
					}
				}
			}
		}
		
		return expedientRepository.save(expedient);
    }
	
	@Transactional
	public List<Expedient> findByMunicipi(Integer page, Integer size,Long municipi) {
		Predicate predicate = QExpedient.expedient.professional.municipi.id.eq(municipi);
		
		return expedientRepository.findAll (predicate,
				PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "codi", "dataCreacio"))).getContent();
	
    }
	
	@Transactional
	public Iterable<Expedient> findByMunicipi(Long municipi) {
		Predicate predicate = QExpedient.expedient.professional.municipi.id.eq(municipi);
		
		return expedientRepository.findAll (predicate);
	
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
