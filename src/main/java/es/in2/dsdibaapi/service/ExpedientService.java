package es.in2.dsdibaapi.service;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Avaluacio;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Valoracio;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.ExpedientRepository;

@Service
public class ExpedientService {
	
	@Autowired
	ExpedientService() {}
	
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
	private AmbitService ambitService;
	
	@Transactional
	public Expedient findById(Long id) {
		return expedientRepository.findById(id).get();
    }
	
	public Expedient save(Expedient expedient, Long versio) {
		VersioModel v = versioModelService.findById(versio);
		
		expedient.setVersioModel(v);
		
		return expedientRepository.save(expedient);
    }
	
	public Expedient save(Expedient expedient) {
		return expedientRepository.save(expedient);
    }
	
	public Expedient avaluar (Expedient exp) {
		Stream<Diagnostic> d = null;
		
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
				
				if (avaluacio.getRisc().getID() == vulnerabilitat.getID()) {
					total += a.getValVulnerabilitat();
				} 
				else if (avaluacio.getRisc().getID() == risc.getID()) {
					total += a.getValRisc();
				}
				else if (avaluacio.getRisc().getID() == altRisc.getID()) {
					total += a.getValAltrisc();
				}
			}
			
		}
		
		valoracio.setTotal(total);
		exp.setValoracio(valoracio);
		valoracioService.save(valoracio);
		
		
		return exp;		
	}
	
	public RiscService.Tipus avaluar (Expedient exp, Ambit a) {
		
		// Avaluacio de Diagnostics
		
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
	
	
/*	public Valoracio avaluar (Expedient exp) {
		Stream<Diagnostic> d = null;
		
		List<Ambit> ambits = ambitService.findAll();	
		
		Avaluacio avaluacio = null;
		
		Valoracio valoracio = valoracioService.save(Valoracio.builder().expedient(exp).build());
		
		for (Ambit a:ambits) {
			
			if (a.getVulnerabilitat() != null) {
				avaluacio = Avaluacio.builder()
							.ambit(a)
							.valoracio(valoracio)
							.risc(riscService.findByDescription(avaluar (exp,a))).build();
				
				avaluacioService.save(avaluacio);
			}
			
		}
		
		
		return valoracio;		
	}
	
	public RiscService.Tipus avaluar (Expedient exp, Ambit a) {
		TreeSet<Diagnostic> t = exp.getDiagnostic().stream().filter(x -> x.getEntorn().getAmbit().getID()==a.getID())
				.sorted((Diagnostic o1, Diagnostic o2)->o2.getFactor().getValue().compareTo(o1.getFactor().getValue()))
				.collect(Collectors.toCollection(
					      () -> new TreeSet<Diagnostic>((p1, p2) -> p1.getSituacioSocial().getID().compareTo(p2.getSituacioSocial().getID())) 
					));
		
		Double count = 0d;
		
		Risc vulnerabilitat = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
		Risc risc = riscService.findByDescription(RiscService.Tipus.RISC);	
		
		
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
	}*/
}
