package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.model.QPregunta;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;
import es.in2.dsdibaapi.repository.PreguntaRepository;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EntornService;
import es.in2.dsdibaapi.service.FactorEconomicService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.PreguntaService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.SituacioSocialService;

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
	private DiagnosticService expedientService;
	
	@Autowired
	private SituacioSocialService situacioSocialService;
	
	@Autowired
	private FactorEconomicService factorEconomicService;
	
	@Autowired
	FactorEconomicRepository factorRepository;
	
	
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
		
		return preguntaRepository.findAll(predicate);
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
		Diagnostic diag = expedientService.findById(diagnostic);
				
		pregunta.setDiagnostic(diag);
	//	pregunta.setEntorn(pregunta.getSituacioSocial().getEntorn());
		
		if (diag.getVersioModel().getPreguntaEconomica().equals(pregunta.getSituacioSocial().getId())) {
			avaluacioEconomica (pregunta);
		} else {
			pregunta.setFactor(riscService.findByDescription(avaluar(pregunta)));
		}
		
						
		
		return preguntaRepository.save(pregunta);
	}
	
	
	private RiscService.Tipus avaluar (Pregunta d) {
		SituacioSocial ss = situacioSocialService.findById(d.getSituacioSocial().getId());
		Entorn e = entornService.findById(ss.getEntorn().getId());
		
		if ( d.getGravetat() == null || d.getFrequencia() == null ||
				(d.getFrequencia().getDescripcio().equalsIgnoreCase("sense valoració") &&
						d.getGravetat().getDescripcio().equalsIgnoreCase("alta"))) {
			return RiscService.Tipus.ALT_RISC;
		}
		else if (d.getFrequencia().getDescripcio().equalsIgnoreCase("sense valoració")) {
			if(d.getGravetat().getDescripcio().equalsIgnoreCase("moderada")) {
				return RiscService.Tipus.RISC;
			} else {
				return RiscService.Tipus.VULNERABILITAT;
			}
		}
		else if(e.getAmbit().getDescripcio().contains("MATERIAL") &&
				d.getGravetat().getDescripcio().equalsIgnoreCase("moderada") &&
				d.getFrequencia().getDescripcio().equalsIgnoreCase("continua") &&
				!d.getSituacioSocial().getSocial().contains("H.1")) {
			return RiscService.Tipus.RISC;
		}
		else if(e.getAmbit().getDescripcio().contains("MATERIAL")  &&
				d.getGravetat().getDescripcio().equalsIgnoreCase("alta") &&
				d.getFrequencia().getDescripcio().equalsIgnoreCase("ocasional") &&
				!d.getSituacioSocial().getSocial().contains("H.1")) {
			return RiscService.Tipus.VULNERABILITAT;
		}
		else {
			int res = d.getGravetat().getValue()*d.getFrequencia().getValue();
			if (res <= 1) {
				return RiscService.Tipus.VULNERABILITAT;
			}
			else if (res <= 3) {
				return RiscService.Tipus.RISC;
			}			
		}	
		
		return RiscService.Tipus.ALT_RISC;
	}

	private void avaluacioEconomica (Pregunta d) {
		
		if (d.getFactorEconomic() != null && d.getFactorEconomic().size() != 0) {
			
			List<FactorEconomic> factors = factorRepository.findAll();
					
			Integer n = d.getFactorEconomic().size();
			
			Boolean xarxaFamiliar = false;
			Boolean dificultats = false;
			
			for (FactorEconomic f:d.getFactorEconomic()) {
				if (f.getId() == factors.get(factors.size()-1).getId()) {
					n--;
					dificultats = true;
				}
				else if (f.getId() == factors.get(factors.size()-2).getId()) {
					n--;
					xarxaFamiliar = true;
				}
			}
				
			if (n < 3) {
				d.setGravetat(null);
			} 
			else if (n < 5) {
				if (xarxaFamiliar) {
					d.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.BAIXA.toString()));
				} else {
					d.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.MODERADA.toString()));
				}
			} 
			else if (n < 7 && xarxaFamiliar) {			
				d.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.MODERADA.toString()));
			} else {
				d.setGravetat(gravetatService.findByDescription(GravetatService.Tipus.ALTA.toString()));			
			} 
			
			if (d.getGravetat() !=null && d.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.ALTA.toString())) {
				if (dificultats) {
					d.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.CONTNUA.toString()));
				} else {
					d.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.OCASIONAL.toString()));
				}
			}
			
			if (d.getGravetat() == null) {
				d.setFactor(riscService.findByDescription(RiscService.Tipus.SENSE_VALORACIO));
			}
			else if (d.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.BAIXA.toString())) {
				d.setFactor(riscService.findByDescription(RiscService.Tipus.VULNERABILITAT));
			}
			else if (d.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.MODERADA.toString()) ||
					(d.getGravetat().getDescripcio().equalsIgnoreCase(GravetatService.Tipus.ALTA.toString()) &&
							d.getFrequencia().getDescripcio().equalsIgnoreCase(FrequenciaService.Tipus.OCASIONAL.toString()))) {
				d.setFactor(riscService.findByDescription(RiscService.Tipus.RISC));
			}
			else {
				d.setFactor(riscService.findByDescription(RiscService.Tipus.ALT_RISC));
			}
		}
		else {
			d.setFactor(riscService.findByDescription(RiscService.Tipus.SENSE_VALORACIO));
		}
	}
}

