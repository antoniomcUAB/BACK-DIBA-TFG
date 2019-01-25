package es.in2.dsdibaapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Avaluacio;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.QExpedient;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Valoracio;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.service.AvaluacioService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.ValoracioService;
import es.in2.dsdibaapi.service.VersioModelService;

@Service
public class ExpedientServiceImpl implements ExpedientService{
	
	@Autowired
	ExpedientServiceImpl() {}
	
	@Autowired
	ExpedientRepository expedientRepository;
	
	@Autowired
	RiscService riscService;
	
	@Autowired
	ValoracioService valoracioService;
	
	@Autowired
	AvaluacioService avaluacioService;
	
	@Autowired
	private VersioModelService versioModelService;
	
	@Autowired
	private AmbitServiceImpl ambitService;
	
	@Transactional
	public Expedient findById(Long id) {
		return expedientRepository.findById(id).get();
    }
	
	@Transactional
	public Iterable<Expedient> findByExpedient(String expedient) {
		Predicate predicate = QExpedient.expedient1.expedient.eq(expedient);
		
		return expedientRepository.findAll(predicate);
    }
	
	@Transactional
	public Iterable<Expedient> findByMunicipi(Long municipi) {
		Predicate predicate = QExpedient.expedient1.professional.municipi.ID.eq(municipi);
		
		return expedientRepository.findAll(predicate,new Sort(Sort.Direction.ASC, "expedient", "DATA"));
    }
	
	public Expedient save(Expedient expedient, Long versio) {
		VersioModel v = versioModelService.findById(versio);
		
		expedient.setVersioModel(v);
		expedient.setEstat(Estat.BORRADOR.toString());
		
		return expedientRepository.save(expedient);
    }
	
	public Expedient save(Expedient expedient) {
		return expedientRepository.save(expedient);
    }
	
	public Expedient avaluar (Long expedient) {	
		
		Expedient exp = expedientRepository.findById(expedient).get();
		
		List<Ambit> ambits = ambitService.findAll();	
		
		Avaluacio avaluacio = null;
		
		
		Valoracio valoracio = null;
		
		if (exp.getValoracio() != null) {
			valoracio = exp.getValoracio();
		}
		else {
			valoracio = valoracioService.save(Valoracio.builder().build());
		}
		
		Risc vulnerabilitat = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
		Risc risc = riscService.findByDescription(RiscService.Tipus.RISC);
		Risc altRisc = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
		
		Double total = 0d;
		
		for (Ambit a:ambits) {
			
			if (a.getVulnerabilitat() != null) {
				avaluacio = Avaluacio.builder()
							.ambit(a)
							.valoracio(valoracio)
							.risc(riscService.findByDescription(avaluar (exp,a))).build();
				
				avaluacioService.save(avaluacio);
				
				if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getID() == vulnerabilitat.getID()) ||
						avaluacio.getRisc().getID() == vulnerabilitat.getID()) {
					total += a.getValVulnerabilitat();
				} 
				else if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getID() == risc.getID()) ||
						avaluacio.getRisc().getID() == risc.getID()) {
					total += a.getValRisc();
				}
				else if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getID() == altRisc.getID()) ||
						avaluacio.getRisc().getID() == altRisc.getID()) {
					total += a.getValAltrisc();
				}
			}
			
		}
		
		valoracio.setTotal(total);
		valoracio.setData(new Date());
		exp.setValoracio(valoracio);
		exp.setEstat(Estat.VALIDAT.toString());
		valoracioService.save(valoracio);
		exp=save (exp);
		
		return exp;		
	}
	
	public RiscService.Tipus avaluar (Expedient exp, Ambit a) {
		
		// Avaluació dels diagnóstics per àmbit
		
		TreeSet<Diagnostic> t = exp.getDiagnostic().stream().filter(x -> x.getEntorn().getAmbit().getID()==a.getID())
				.sorted((Diagnostic o1, Diagnostic o2)->o2.getFactor().getValue().compareTo(o1.getFactor().getValue()))
				.collect(Collectors.toCollection(
					      () -> new TreeSet<Diagnostic>((p1, p2) -> p1.getSituacioSocial().getID().compareTo(p2.getSituacioSocial().getID())) 
					));
		
		Double count = 0d;
		
		Risc vulnerabilitat = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
		Risc risc = riscService.findByDescription(RiscService.Tipus.RISC);	
		
		// Avaluacio de Factors de context
		for (Contextualitzacio c:exp.getContextualitzacio()) {
			if (c.getMesUc()!=null && c.getMesUc()) {
				count += c.getFactor().getFctots();
			}
			else {
				count += c.getFactor().getFc1m();
			}
		}
		
		for (Diagnostic d:t) {
			if (d.getFactor().equals(vulnerabilitat)) {
				count += d.getSituacioSocial().getVulnerabilitat();
			}
			else if (d.getFactor().equals(risc)) {
				count += d.getSituacioSocial().getRisc();
			} 
			else {
				count += d.getSituacioSocial().getAltRisc();
			}
		}
		
		if ( count == 0d) {
			return RiscService.Tipus.SENSE_VALORACIO;
		}
		else if (count < a.getVulnerabilitat()) {
			return RiscService.Tipus.VULNERABILITAT;
		}
		else if (count < a.getRisc()) {
			return RiscService.Tipus.RISC;
		}
		
		return RiscService.Tipus.ALT_RISC;
	}
	
}
