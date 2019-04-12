package es.in2.dsdibaapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.model.QPregunta;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.repository.PreguntaRepository;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EntornService;
import es.in2.dsdibaapi.service.FactorEconomicService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.PreguntaService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.SituacioSocialService;
import es.in2.dsdibaapi.valoracio.ambit.AmbitEvalFactory;

@Service
public class PreguntaServiceImpl implements PreguntaService{
	
	
	
	@Autowired
	PreguntaServiceImpl() {}
	
	@Autowired
	private PreguntaRepository preguntaRepository;	
	
	@Autowired
	private RiscService riscService;
	
	@Autowired
	private EntornService entornService;
	
	@Autowired
	private GravetatService gravetatService;
	
	@Autowired
	private FrequenciaService frequenciaService;
	
	@Autowired
	private DiagnosticService diagnosticService;
	
	@Autowired
	private SituacioSocialService situacioSocialService;
	
	@Autowired
	FactorEconomicService factorService;
	
	
    public Pregunta findById(Long id) {
		return preguntaRepository.findById(id).get();
    }
    
    public Boolean existsById(Long id) {
		return preguntaRepository.existsById(id);
    }

    
    public void delete(Long id) {
    	preguntaRepository.deleteById(id);
    }

	
    public Iterable<Pregunta> findByDiagnosticEntorn(Long diagnostic,Long entorn) {
		
    	Predicate predicate =null;
    	
    	if (entorn != null) {
    		predicate = QPregunta.pregunta.diagnostic.id.eq(diagnostic)
    				.and(QPregunta.pregunta.situacioSocial.entorn.id.eq(entorn));
    	} else {
    		predicate = QPregunta.pregunta.diagnostic.id.eq(diagnostic);
    	}
		
		return preguntaRepository.findAll(predicate, Sort.by(Sort.Direction.ASC, "id"));
    }
    
    public void deleteByDiagnosticAmbit(Long diagnostic,Long ambit) {
		
    	Predicate predicate =null;    	
    	    	
		predicate = QPregunta.pregunta.diagnostic.id.eq(diagnostic)
				.and(QPregunta.pregunta.situacioSocial.entorn.ambit.id.eq(ambit));
		
		Iterable<Pregunta> llista = preguntaRepository.findAll(predicate);
		
		preguntaRepository.deleteAll(llista);
    }
    
    public void deleteByDiagnosticSituacioSocial(Long diagnostic,Long sotiacioSocial) {
		
    	Predicate predicate =null;    	
    	
		predicate = QPregunta.pregunta.diagnostic.id.eq(diagnostic)
				.and(QPregunta.pregunta.situacioSocial.id.eq(sotiacioSocial));
		
		Iterable<Pregunta> llista = preguntaRepository.findAll(predicate);
		
		preguntaRepository.deleteAll(llista);
    }
    
    
    
    public Pregunta findByDiagnosticSituacioSocial(Long diagnostic,Long situacioSocial) {
		
    	Predicate predicate =null;
    	
    	
		predicate = QPregunta.pregunta.diagnostic.id.eq(diagnostic)
				.and(QPregunta.pregunta.situacioSocial.id.eq(situacioSocial));
    	
		if (preguntaRepository.findOne(predicate).isPresent()) {		
			return preguntaRepository.findOne(predicate).get();
		}
		
		return null;
    }
    
   
	
	public Pregunta save (Pregunta pregunta, Long diagnostic) {
		Diagnostic diag = diagnosticService.findById(diagnostic);
				
		pregunta.setDiagnostic(diag);
	
		
		if (diag.getVersioModel().getPreguntaEconomica().contains(pregunta.getSituacioSocial().getId().toString())) {
			avaluacioEconomica (pregunta);
		} else {
			pregunta.setFactor(riscService.findByDescription(avaluar(pregunta)));
		}
		
						
		
		return preguntaRepository.save(pregunta);
	}
	
	
	private RiscService.Tipus avaluar (Pregunta d) {
		SituacioSocial ss = situacioSocialService.findById(d.getSituacioSocial().getId());
		Entorn e = entornService.findById(ss.getEntorn().getId());
		
		return AmbitEvalFactory.getEval(d, e.getAmbit());		
	}

	private void avaluacioEconomica (Pregunta p) {
			
		if (p.getSituacioSocial().getSocial().toLowerCase().contains("e1")) {
			evalE1 (p);
		} else {
			evalE2 (p);
		}
		
	}
	
	private void evalE1 (Pregunta p) {
		Iterable<FactorEconomic> factors = factorService.findAll(Optional.ofNullable(p.getDiagnostic().getVersioModel().getId()));
				
		FactorEconomic f1= StreamSupport.stream(factors.spliterator(), false).findFirst().get();
		
		FactorEconomic fFamiliar= StreamSupport.stream(factors.spliterator(), false)
				.filter( f -> f.getDescripcio().toLowerCase().contains("Existeix una xarxa familiar".toLowerCase()))
				.findFirst().get();
		
		FactorEconomic fDificultats= StreamSupport.stream(factors.spliterator(), false)
				.filter( f -> f.getDescripcio().toLowerCase().contains("Les dificultats en".toLowerCase()))
				.findFirst().get();
		
		Integer n = p.getFactorEconomic().size();
		
		Boolean xarxaFamiliar = false;
		Boolean dificultats = false;
		Boolean pagaHipoteca = false;
		
		for (FactorEconomic f:p.getFactorEconomic()) {
			if (f.getId() == f1.getId()) {
				n--;
				pagaHipoteca = true;
			}
			else if (f.getId() == fDificultats.getId()) {
				n--;
				dificultats = true;
			}
			else if (f.getId() == fFamiliar.getId()) {
				n--;
				xarxaFamiliar = true;
			}
		}
			
		if (xarxaFamiliar && pagaHipoteca) {			
			p.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.MODERADA.toString()));
			
			if (dificultats) {
				p.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.CONTINUA.toString()));
			} else {
				p.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.OCASIONAL.toString()));
			}
			
		} else {
			p.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.ALTA.toString()));			
		} 
		
		if (p.getGravetat() !=null && p.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.ALTA.toString())) {
			if (dificultats) {
				p.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.CONTINUA.toString()));
			} else {
				p.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.OCASIONAL.toString()));
			}
		}
		
		if (p.getGravetat() == null) {
			p.setFactor(riscService.findByDescription(RiscService.Tipus.SENSE_VALORACIO));
		}
		else if (p.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.MODERADA.toString()) &&
						p.getFrequencia().getDescripcio().equalsIgnoreCase(FrequenciaService.Tipus.OCASIONAL.toString())) {
			p.setFactor(riscService.findByDescription(RiscService.Tipus.RISC));
		}						
		else {
			p.setFactor(riscService.findByDescription(RiscService.Tipus.ALT_RISC));
		}
		
	}
	
	private void evalE2 (Pregunta p) {		
		
		
			if (p.getFactorEconomic() != null && p.getFactorEconomic().size() != 0) {			
				
				Iterable<FactorEconomic> factors = factorService.findAll(Optional.ofNullable(p.getDiagnostic().getVersioModel().getId()));
				
				
				
				FactorEconomic fFamiliar= StreamSupport.stream(factors.spliterator(), false)
						.filter( f -> f.getDescripcio().toLowerCase().contains("Existeix una xarxa familiar".toLowerCase()))
						.findFirst().get();
				
				FactorEconomic fDificultats= StreamSupport.stream(factors.spliterator(), false)
						.filter( f -> f.getDescripcio().toLowerCase().contains("Les dificultats en".toLowerCase()))
						.findFirst().get();
				
				Integer n = p.getFactorEconomic().size();
				
				Boolean xarxaFamiliar = false;
				Boolean dificultats = false;
				
				for (FactorEconomic f:p.getFactorEconomic()) {
					if (f.getId() == fDificultats.getId()) {
						n--;
						dificultats = true;
					}
					else if (f.getId() == fFamiliar.getId()) {
						n--;
						xarxaFamiliar = true;
					}
				}
					
				if (n < 3) {
					p.setGravetat(null);
				} 
				else if (n < 5) {
					if (xarxaFamiliar) {
						p.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.BAIXA.toString()));
					} else {
						p.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.MODERADA.toString()));
					}
				} 
				else if (n < 7 && xarxaFamiliar) {			
					p.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.MODERADA.toString()));
				} else {
					p.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.ALTA.toString()));			
				} 
				
				if (p.getGravetat() !=null && p.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.ALTA.toString())) {
					if (dificultats) {
						p.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.CONTINUA.toString()));
					} else {
						p.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.OCASIONAL.toString()));
					}
				}
				
				if (p.getGravetat() == null) {
					p.setFactor(riscService.findByDescription(RiscService.Tipus.SENSE_VALORACIO));
				}
				else if (p.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.BAIXA.toString())) {
					p.setFactor(riscService.findByDescription(RiscService.Tipus.VULNERABILITAT));
				}
				else if (p.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.MODERADA.toString()) ||
						(p.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.ALTA.toString()) &&
								p.getFrequencia().getDescripcio().equalsIgnoreCase(FrequenciaService.Tipus.OCASIONAL.toString()))) {
					p.setFactor(riscService.findByDescription(RiscService.Tipus.RISC));
				}
				else {
					p.setFactor(riscService.findByDescription(RiscService.Tipus.ALT_RISC));
				}
			}
			else {
				p.setFactor(riscService.findByDescription(RiscService.Tipus.SENSE_VALORACIO));
			}
				
	}
}

