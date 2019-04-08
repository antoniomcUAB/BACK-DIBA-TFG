package es.in2.dsdibaapi.service.impl;

import static es.in2.dsdibaapi.model.predicate.DiagnosticPredicate.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

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
	
/*	public List<Expedient> findAll() {
		return expedientRepository.findAll(Sort.by(Sort.Direction.DESC, "dataCreacio"));
    }
	*/
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
	public Page<Expedient> findByMunicipi(Integer page, Integer size,Long municipi, String codi, String professional, String estat, String sort, String dataCreacio, String dataValidacio) {
				
		BooleanExpression where = QExpedient.expedient.professional.municipi.id.eq(municipi);
		
		Sort s = Sort.by(Sort.Direction.DESC, "dataCreacio");
		
		Direction d = Sort.Direction.DESC;
		
		if (codi != null && !codi.isEmpty()) {
			where = where.and(QExpedient.expedient.codi.containsIgnoreCase(codi));
		}
		
		if (professional != null && !professional.isEmpty()) {
			where = where.and(QExpedient.expedient.professional.nomComplet.containsIgnoreCase(professional.trim().toLowerCase()));
		}
		
		if (estat != null && !estat.isEmpty()) {
			where = where.and(QExpedient.expedient.estat.descripcio.equalsIgnoreCase(estat));
		}
		
		if (dataCreacio != null && !dataCreacio.isEmpty()) {
			Date dateIni,dateFin;
			try {
				dateIni = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataCreacio+" 00:00");
				dateFin = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataCreacio+" 23:59");
				where = where.and(QExpedient.expedient.dataCreacio.between(dateIni, dateFin));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (dataValidacio != null && !dataValidacio.isEmpty()) {
			Date dateIni,dateFin;
			try {
				dateIni = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataValidacio+" 00:00");
				dateFin = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dataValidacio+" 23:59");
				where = where.and(QExpedient.expedient.dataValidacio.between(dateIni, dateFin));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (sort != null && !sort.isEmpty() && sort.contains(",")) {
		
			if (sort.split(",")[1].equalsIgnoreCase("asc")) {
				d = Sort.Direction.ASC;
			} 
			
			if (sort.equalsIgnoreCase("professional" )) {
				s = Sort.by(d, "professional.nom", "professional.cognom1", "professional.cognom2");
			}
			else if (sort.equalsIgnoreCase("estat" )) {
				s = Sort.by(d, "estat.descripcio");
			}
			else {
				s = Sort.by(d, sort.split(",")[0]);
			}
			
		} 
		
		Page<Expedient> expedients = expedientRepository.findAll (where,
				PageRequest.of(page, size, s));
				
		
		expedients.forEach(n -> { 
					if (n.getDiagnostic()!=null 
										&& !n.getDiagnostic().isEmpty()) {
											n.setDiagnosticsValidats(diagnosticsValidats(n.getDiagnostic())); 
										}
							});
		
		
		return expedients;
	
    }
	
	@Transactional
	public Iterable<Expedient> findByMunicipi(Long municipi) {
		Predicate predicate = QExpedient.expedient.professional.municipi.id.eq(municipi);
		
		return expedientRepository.findAll (predicate, Sort.by(Sort.Direction.DESC, "dataCreacio"));
	
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
