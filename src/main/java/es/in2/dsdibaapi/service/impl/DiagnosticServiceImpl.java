package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Economia;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.QDiagnostic;
import es.in2.dsdibaapi.repository.DiagnosticRepository;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EntornService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.RiscService;

@Service
public class DiagnosticServiceImpl implements DiagnosticService{
	
	@Autowired
	DiagnosticServiceImpl() {}
	
	@Autowired
	private DiagnosticRepository diagnosticRepository;
	
	@Autowired
	private EntornService entornService;
	
	@Autowired
	private RiscService riscService;
	
	@Autowired
	private GravetatService gravetatService;
	
	@Autowired
	private FrequenciaService frequenciaService;
	
	@Autowired
	private ExpedientService expedientService;
	
	@Autowired
	FactorEconomicRepository factorRepository;
	
	@Autowired
	private Environment env;
	
	
    public Diagnostic findById(Long id) {
		return diagnosticRepository.findById(id).get();
    }
    
    public Boolean existsById(Long id) {
		return diagnosticRepository.existsById(id);
    }

	
    public Iterable<Diagnostic> findByExpedientEntorn(Long expedient,Long entorn) {
		
    	Predicate predicate =null;
    	
    	if (entorn != null) {
    		predicate = QDiagnostic.diagnostic.expedient.ID.eq(expedient)
    				.and(QDiagnostic.diagnostic.entorn.ID.eq(entorn));
    	} else {
    		predicate = QDiagnostic.diagnostic.expedient.ID.eq(expedient);
    	}
		
		return diagnosticRepository.findAll(predicate);
    }
    
    public Diagnostic findByExpedientSituacioSocial(Long expedient,Long situacioSocial) {
		
    	Predicate predicate =null;
    	
    	
		predicate = QDiagnostic.diagnostic.expedient.ID.eq(expedient)
				.and(QDiagnostic.diagnostic.situacioSocial.ID.eq(situacioSocial));
    	 
		
		return diagnosticRepository.findOne(predicate).get();
    }
    
   
	
	public Diagnostic save (Diagnostic diagnostic, Long expedient, Long entorn) {
		Expedient exp = expedientService.findById(expedient);
		
		Entorn ent = entornService.findById(entorn);
		
		diagnostic.setExpedient(exp);
		diagnostic.setEntorn(ent);
		
		if (exp.getDiagnosticEconomic().equals(diagnostic.getSituacioSocial().getID())) {
			avaluacioEconomica (diagnostic);
		} 
		
		diagnostic.setFactor(riscService.findByDescription(avaluar(diagnostic)));				
		
		return diagnosticRepository.save(diagnostic);
	}
	
	
	private RiscService.Tipus avaluar (Diagnostic d) {
		if ( d.getGravetat() == null || d.getFrequencia() == null ||
				(d.getFrequencia().getDESCRIPCIO().equalsIgnoreCase("sense valoració") &&
						d.getGravetat().getDESCRIPCIO().equalsIgnoreCase("alta"))) {
			return RiscService.Tipus.ALT_RISC;
		}
		else if (d.getFrequencia().getDESCRIPCIO().equalsIgnoreCase("sense valoració")) {
			if(d.getGravetat().getDESCRIPCIO().equalsIgnoreCase("moderada")) {
				return RiscService.Tipus.RISC;
			} else {
				return RiscService.Tipus.VULNERABILITAT;
			}
		}
		else {
			int gravetat = Integer.valueOf(env.getProperty("eval.gravetat."+d.getGravetat().getDESCRIPCIO().toLowerCase()));
			int frequencia = Integer.valueOf(env.getProperty("eval.frequencia."+d.getFrequencia().getDESCRIPCIO().toLowerCase()));
			
			if ((gravetat*frequencia) <= 1) {
				return RiscService.Tipus.VULNERABILITAT;
			}
			else if ((gravetat*frequencia) <= 3) {
				return RiscService.Tipus.RISC;
			}			
		}	
		
		return RiscService.Tipus.ALT_RISC;
	}

	private void avaluacioEconomica (Diagnostic d) {
		
		if (d.getFactorEconomic() != null && d.getFactorEconomic().size() != 0) {
			
			List<FactorEconomic> factors = factorRepository.findAll();
					
			Integer n = d.getFactorEconomic().size();
			
			Boolean xarxaFamiliar = false;
			Boolean dificultats = false;
			
			for (FactorEconomic f:d.getFactorEconomic()) {
				if (f.getID() == factors.get(factors.size()-1).getID()) {
					n--;
					dificultats = true;
				}
				else if (f.getID() == factors.get(factors.size()-2).getID()) {
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
			
			if (d.getGravetat() !=null && d.getGravetat().getDESCRIPCIO().equalsIgnoreCase(GravetatService.Tipus.ALTA.toString())) {
				if (dificultats) {
					d.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.CONTNUA.toString()));
				} else {
					d.setFrequencia(frequenciaService.findByDescription(FrequenciaService.Tipus.OCASIONAL.toString()));
				}
			}
			
			if (d.getGravetat() == null) {
				d.setRisc(riscService.findByDescription(RiscService.Tipus.SENSE_VALORACIO));
			}
			else if (d.getGravetat().getDESCRIPCIO().equalsIgnoreCase(GravetatService.Tipus.BAIXA.toString())) {
				d.setRisc(riscService.findByDescription(RiscService.Tipus.VULNERABILITAT));
			}
			else if (d.getGravetat().getDESCRIPCIO().equalsIgnoreCase(GravetatService.Tipus.MODERADA.toString()) ||
					(d.getGravetat().getDESCRIPCIO().equalsIgnoreCase(GravetatService.Tipus.ALTA.toString()) &&
							d.getFrequencia().getDESCRIPCIO().equalsIgnoreCase(FrequenciaService.Tipus.OCASIONAL.toString()))) {
				d.setRisc(riscService.findByDescription(RiscService.Tipus.RISC));
			}
			else {
				d.setRisc(riscService.findByDescription(RiscService.Tipus.ALT_RISC));
			}
		}
		else {
			d.setRisc(riscService.findByDescription(RiscService.Tipus.SENSE_VALORACIO));
		}
	}
}

