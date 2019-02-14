package es.in2.dsdibaapi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.json.AmbitJson;
import es.in2.dsdibaapi.json.EntornJson;
import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Avaluacio;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.model.QDiagnostic;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Valoracio;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.repository.DiagnosticRepository;
import es.in2.dsdibaapi.service.AmbitService;
import es.in2.dsdibaapi.service.AvaluacioService;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.ValoracioService;
import es.in2.dsdibaapi.service.VersioModelService;

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
	private VersioModelService versioModelService;
	
	@Autowired
	private AmbitService ambitService;
	
	@Autowired
	private PreguntaServiceImpl preguntaService;
	
	@Autowired
	private ContextualitzacioServiceImpl contextualitzacioService;
	
	@Transactional
	public Diagnostic findById(Long id) {
		
		Diagnostic d = diagnosticRepository.findById(id).get();
		
		List<Ambit> ambits = ambitService.findAll();
		
		d.setAmbit(new ArrayList<AmbitJson> ());
		
		EntornJson jsonEntorn;
		AmbitJson jsonAmbit;
		
		List<EntornJson> entornsJson = null;
		
		for (Ambit a:ambits) {
			entornsJson = new ArrayList<EntornJson> ();
			for (Entorn e:a.getEntorn()) {
				jsonEntorn = es.in2.dsdibaapi.json.EntornJson.builder()
								.descripcio(e.getDescripcio())
								.id(e.getId()).build();
				jsonEntorn.setPregunta(preguntaService.findByDiagnosticEntorn(d.getId(),e.getId()));
				
				entornsJson.add(jsonEntorn);
			}
			
			jsonAmbit = es.in2.dsdibaapi.json.AmbitJson.builder()
							.id(a.getId())
							.entorn(entornsJson)
							.valAltrisc(a.getValAltrisc())
							.valRisc(a.getValRisc())
							.observacions(a.getObservacions())
							.valVulnerabilitat(a.getValVulnerabilitat())
							.vulnerabilitat(a.getVulnerabilitat())
							.risc(a.getRisc())
							.descripcio(a.getDescripcio()).build();
			jsonAmbit.setContextualitzacio(contextualitzacioService.findByDiagnosticAmbit(d.getId(), a.getId()));
			
			d.getAmbit().add(jsonAmbit);
		}
		
		return d;
    }
	
	@Transactional
	public Iterable<Diagnostic> findByExpedient(String expedient) {
		Predicate predicate = QDiagnostic.diagnostic.expedient.codi.eq(expedient);
		
		return diagnosticRepository.findAll(predicate);
    }
	
	
	
	public Diagnostic save(Diagnostic diagnostic, Long versio) {
		VersioModel v = versioModelService.findById(versio);
		
		diagnostic.setVersioModel(v);
		diagnostic.setEstat(estatService.findByDescripcio(Estat.BORRADOR.toString()));
		
		return diagnosticRepository.save(diagnostic);
    }
	
	public Diagnostic save(Diagnostic diagnostic) {
		return diagnosticRepository.save(diagnostic);
    }
	
	public Diagnostic avaluar (Long diagnostic) {	
		
		Diagnostic diag = findById(diagnostic);
		
		//List<Ambit> ambits = ambitService.findAll();
		
		Ambit ambit = null;
		
		Avaluacio avaluacio = null;
		
		
		Valoracio valoracio = null;
		
		if (diag.getValoracio() != null) {
			valoracio = diag.getValoracio();
			
		}
		else {
			//valoracio = valoracioService.save(Valoracio.builder().build());
			valoracio = Valoracio.builder().build();
			valoracio.setAvaluacio(new ArrayList<Avaluacio> ());
		}
		
		
		
		Risc vulnerabilitat = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
		Risc risc = riscService.findByDescription(RiscService.Tipus.RISC);
		Risc altRisc = riscService.findByDescription(RiscService.Tipus.ALT_RISC);
		
		Double total = 0d;
		
		/*if (valoracio.getAvaluacio() != null && valoracio.getAvaluacio().size() == 3) {
			for (Avaluacio av:valoracio.getAvaluacio()) {
				av.setRisc(riscService.findByDescription(avaluar (diag,av.getAmbit())));
				
				if ((av.getRiscProfessional() != null && av.getRiscProfessional().getId() == vulnerabilitat.getId()) ||
						av.getRisc().getId() == vulnerabilitat.getId()) {
					total += av.getAmbit().getValVulnerabilitat();
				} 
				else if ((av.getRiscProfessional() != null && av.getRiscProfessional().getId() == risc.getId()) ||
						av.getRisc().getId() == risc.getId()) {
					total += av.getAmbit().getValRisc();
				}
				else if ((av.getRiscProfessional() != null && av.getRiscProfessional().getId() == altRisc.getId()) ||
						av.getRisc().getId() == altRisc.getId()) {
					total += av.getAmbit().getValAltrisc();
				}
			}
		} else {
		*/
			for (AmbitJson a:diag.getAmbit()) {
				ambit = ambitService.findById(a.getId());
			/*	if (ambit.getVulnerabilitat() != null && 
						(a.getContextualitzacio()!=null || a.getEntorn().getPregunta()!=null) ) {*/
				for (EntornJson e:a.getEntorn()) {
					if (a.getVulnerabilitat() != null &&
							StreamSupport.stream(a.getContextualitzacio().spliterator(),false).count() > 0 || 
							StreamSupport.stream(e.getPregunta().spliterator(),false).count() > 0) {
						
						if (valoracio.getAvaluacio() != null && valoracio.getAvaluacio().size() == 3) {
							for (Avaluacio av:valoracio.getAvaluacio()) {
								if (av.getAmbit().getId() == a.getId()) {
									avaluacio =av; 
									break;
								}
							}
						}
						else {
							avaluacio = Avaluacio.builder()
										.ambit(ambit)
										.valoracio(valoracio)
										.risc(riscService.findByDescription(avaluar (diag,a,e))).build();
							
							
							valoracio.getAvaluacio().add(avaluacio);
						}
					
					if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getId() == vulnerabilitat.getId()) ||
							avaluacio.getRisc().getId() == vulnerabilitat.getId()) {
						total += a.getValVulnerabilitat();
					} 
					else if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getId() == risc.getId()) ||
							avaluacio.getRisc().getId() == risc.getId()) {
						total += a.getValRisc();
					}
					else if ((avaluacio.getRiscProfessional() != null && avaluacio.getRiscProfessional().getId() == altRisc.getId()) ||
							avaluacio.getRisc().getId() == altRisc.getId()) {
						total += a.getValAltrisc();
					}
				}
				}
			}
	//	}
		
		valoracio.setTotal(total);
		valoracio.setData(new Date());
		diag.setValoracio(valoracio);
		diag.setEstat(estatService.findByDescripcio(Estat.VALIDAT.toString()));
		diag.getExpedient().setDataValidacio(valoracio.getData());
		//valoracioService.save(valoracio);
		diag=save (diag);
		expedientService.eval(diag.getExpedient());
		
		
		return diag;		
	}
	
	
	public RiscService.Tipus avaluar (Diagnostic diagnostic,AmbitJson a, EntornJson e) {
		
		
		Double count = 0d;
		
		if (e.getPregunta() != null) {
		
			Risc vulnerabilitat = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
			Risc risc = riscService.findByDescription(RiscService.Tipus.RISC);	
			
			// Avaluacio de Factors de context
			for (Contextualitzacio c:a.getContextualitzacio()) {
				if (c.getMesUc()!=null && c.getMesUc()) {
					count += c.getFactor().getFctots();
				}
				else {
					count += c.getFactor().getFc1m();
				}
			}
			
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
	
	/*public RiscService.Tipus avaluar (Diagnostic diagnostic, Ambit a) {
		
		// Avaluació dels diagnóstics per àmbit
		
		TreeSet<Pregunta> t = diagnostic.getPregunta().stream().filter(x -> x.getEntorn().getAmbit().getId()==a.getId())
				.sorted((Pregunta o1, Pregunta o2)->o2.getFactor().getValue().compareTo(o1.getFactor().getValue()))
				.collect(Collectors.toCollection(
					      () -> new TreeSet<Pregunta>((p1, p2) -> p1.getSituacioSocial().getId().compareTo(p2.getSituacioSocial().getId())) 
					));
		
		TreeSet<Contextualitzacio> ctx = diagnostic.getContextualitzacio().stream().filter(x -> x.getFactor().getAmbit().getId()==a.getId())				
				.collect(Collectors.toCollection(
					      () -> new TreeSet<Contextualitzacio>((p1, p2) -> Long.valueOf(p1.getFactor().getId()).compareTo(Long.valueOf(p2.getFactor().getId()))) 
					));
		
		Double count = 0d;
		
		if (t.size() > 0) {
		
			Risc vulnerabilitat = riscService.findByDescription(RiscService.Tipus.VULNERABILITAT);
			Risc risc = riscService.findByDescription(RiscService.Tipus.RISC);	
			
			// Avaluacio de Factors de context
			for (Contextualitzacio c:ctx) {
				if (c.getMesUc()!=null && c.getMesUc()) {
					count += c.getFactor().getFctots();
				}
				else {
					count += c.getFactor().getFc1m();
				}
			}
			
			for (Pregunta d:t) {
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