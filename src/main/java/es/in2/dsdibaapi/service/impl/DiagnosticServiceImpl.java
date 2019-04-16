package es.in2.dsdibaapi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.json.EntornJson;
import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.AmbitDiagnostic;
import es.in2.dsdibaapi.model.Avaluacio;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.model.QDiagnostic;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Valoracio;
import es.in2.dsdibaapi.repository.DiagnosticRepository;
import es.in2.dsdibaapi.service.AmbitService;
import es.in2.dsdibaapi.service.AvaluacioService;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.ValoracioService;

@Service
public class DiagnosticServiceImpl implements DiagnosticService{
	
	@Autowired
	DiagnosticServiceImpl() {}
	
	@Autowired
	DiagnosticRepository diagnosticRepository;
	
	@Autowired
	RiscService riscService;
	
	@Autowired
	ValoracioService valoracioService;
	
	@Autowired
	AvaluacioService avaluacioService;
	
	@Autowired
	EstatService estatService;
	
	@Autowired
	ExpedientService expedientService;	
	
	@Autowired
	private AmbitService ambitService;
	
	@Autowired
	private PreguntaServiceImpl preguntaService;

	
	@Autowired
	private ContextualitzacioServiceImpl contextualitzacioService;
	
	@Transactional
	public Diagnostic findById(Long id) {
		
		
		
		Diagnostic d = diagnosticRepository.findById(id).get();
		
		
		
		if (d.getAmbit() != null) {		
			
			EntornJson jsonEntorn;
			
			List<EntornJson> entornsJson = null;
			
			for (AmbitDiagnostic a:d.getAmbit()) {
				entornsJson = new ArrayList<EntornJson> ();
				for (Entorn e:a.getAmbit().getEntorn()) {
					jsonEntorn = es.in2.dsdibaapi.json.EntornJson.builder()
									.descripcio(e.getDescripcio())
									.id(e.getId()).build();
					jsonEntorn.setPregunta(preguntaService.findByDiagnosticEntorn(d.getId(),e.getId()));
					
					entornsJson.add(jsonEntorn);
				}
				
				
				a.setEntorn(entornsJson);
				a.setContextualitzacio(contextualitzacioService.findByDiagnosticAmbit(d.getId(), a.getAmbit().getId()));
				
			}
		}
		
		return d;
    }
	
	@Transactional
	public Iterable<Diagnostic> findByExpedient(String expedient) {
		Predicate predicate = QDiagnostic.diagnostic.expedient.codi.eq(expedient);
		
		return diagnosticRepository.findAll(predicate,Sort.by(Sort.Direction.DESC, "data"));
    }
	
	
	public Diagnostic save(Diagnostic diagnostic) {
		
		if (diagnostic.getAmbit() == null ||
				diagnostic.getAmbit().isEmpty()) {
			Iterable<Ambit> ambits = ambitService.findAll(diagnostic.getVersioModel().getId());
			diagnostic.setAmbit(new ArrayList<AmbitDiagnostic>());
			for (Ambit a:ambits) {				
				diagnostic.getAmbit().add(AmbitDiagnostic.builder().ambit(a).build());
			}
		}
		else {
			for (AmbitDiagnostic a:diagnostic.getAmbit()) {
				a.setDiagnostic(diagnostic);
			}
		}
		
		return diagnosticRepository.save(diagnostic);
    }
	
	public Diagnostic avaluar (Diagnostic diag) {			
		
		Avaluacio avaluacio = null;	
		
		Valoracio valoracio = null;
		
		if (diag.getValoracio() != null) {
			valoracio = diag.getValoracio();
			
		}
		else {
			valoracio = Valoracio.builder().build();
			valoracio.setAvaluacio(new ArrayList<Avaluacio> ());
		}		
		
		
		Risc vulnerabilitat = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
		Risc risc = riscService.findByDescription(RiscService.Tipus.RISC);
		Risc altRisc = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
		
		Double total = 0d;
		
		for (AmbitDiagnostic a:diag.getAmbit()) {			
			avaluacio = null;
			
			if (diag.getValoracio() != null) {
				for (Avaluacio av:diag.getValoracio().getAvaluacio()) {
					if (av.getAmbit().getId() == a.getId()) {
						avaluacio = av;
						
						break;
					}
				}
			}
			
				
			if (a.getAmbit().getVulnerabilitat() != null) { 
					
				
				if (avaluacio == null) {
					avaluacio = Avaluacio.builder()
								.ambit(a)
								.valoracio(valoracio)
								.risc(riscService.findByDescription(avaluar (a))).build();
					
					
					valoracio.getAvaluacio().add(avaluacio);
				} else {
					avaluacio.setRisc(riscService.findByDescription(avaluar (a)));
				}
			
				if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getId() == vulnerabilitat.getId()) ||
						(avaluacio.getRiscProfessional() == null && avaluacio.getRisc().getId() == vulnerabilitat.getId())) {
					total += a.getAmbit().getValVulnerabilitat();
				} 
				else if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getId() == risc.getId()) ||
						(avaluacio.getRiscProfessional() == null && avaluacio.getRisc().getId() == risc.getId())) {
					total += a.getAmbit().getValRisc();
				}
				else if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getId() == altRisc.getId()) ||
						(avaluacio.getRiscProfessional() == null && avaluacio.getRisc().getId() == altRisc.getId())) {
					total += a.getAmbit().getValAltrisc();
				}
			} 
			// Globalitat del cas
			else if (a.getAmbit().getVulnerabilitat() == null) {
				
				if (avaluacio == null) {
					avaluacio = Avaluacio.builder()
								.ambit(a)
								.valoracio(valoracio).build();
					
					
					valoracio.getAvaluacio().add(avaluacio);
				}
				
				if (total == 0) {
					avaluacio.setRisc(null);
				}
				else if (total <= a.getAmbit().getValVulnerabilitat()) {
					avaluacio.setRisc(vulnerabilitat);
				}
				else if (total < a.getAmbit().getValRisc()) {
					avaluacio.setRisc(risc);
				} else {
					avaluacio.setRisc(altRisc);
				}
			}
			
		}
		
	
		valoracio.setTotal(total);
		valoracio.setData(new Date());
		diag.setValoracio(valoracio);
		
		if (valoracio.getConfirmat()!= null && valoracio.getConfirmat()) {
			diag.setEstat(estatService.findByDescripcio(Estat.VALIDAT.toString()));
		} 
		diag.getExpedient().setDataValidacio(valoracio.getData());
		
		diag=save (diag);
		expedientService.eval(diag.getExpedient());
		
		
		return diag;		
	}
	
	
	public RiscService.Tipus avaluar (AmbitDiagnostic a) {
		
		
		Double count = 0d;
		
		for (EntornJson e:a.getEntorn()) {
		
			if (e.getPregunta() != null) {
			
				Risc vulnerabilitat = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
				Risc risc = riscService.findByDescription(RiscService.Tipus.RISC);			
				
				for (Pregunta d:e.getPregunta()) {
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
			
			}
		}
		
		// Avaluacio de Factors de context
		for (Contextualitzacio c:a.getContextualitzacio()) {
			if (c.getMesUc()!=null && c.getMesUc()) {
				count += c.getFactor().getFctots();
			}
			else {
				count += c.getFactor().getFc1m();
			}
		}
		
		if ( count == 0d) {
			return RiscService.Tipus.SENSE_VALORACIO;
		}
		else if (count < a.getAmbit().getVulnerabilitat()) {
			return RiscService.Tipus.VULNERABILITAT;
		}
		else if (count < a.getAmbit().getRisc()) {
			return RiscService.Tipus.RISC;
		}
		
		return RiscService.Tipus.ALT_RISC;
	}
	
	@Transactional
	public void deletePreguntasByAmbit (Long diagnostic, Long ambit) {
		preguntaService.deleteByDiagnosticAmbit(diagnostic, ambit);
		contextualitzacioService.deleteByDiagnosticAmbit(diagnostic, ambit);
	}
	
}
