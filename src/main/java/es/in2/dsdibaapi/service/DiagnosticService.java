package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.core.Evaluation;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.QDiagnostic;
import es.in2.dsdibaapi.repository.DiagnosticRepository;

@Service
public class DiagnosticService {
	
	@Autowired
	DiagnosticService() {}
	
	@Autowired
	private DiagnosticRepository diagnosticRepository;
	
	@Autowired
	private EntornService entornService;
	
	@Autowired
	private RiscService riscService;
	
	@Autowired
	private ExpedientService expedientService;
	
	@Autowired
	private Environment env;
	
	
    public Diagnostic findById(Long id) {
		return diagnosticRepository.findById(id).get();
    }

	
    public Iterable<Diagnostic> findByExpedientEntorn(Long expedient,Long entorn) {
		
    	Predicate predicate = QDiagnostic.diagnostic.expedient.ID.eq(expedient)
				.and(QDiagnostic.diagnostic.entorn.ID.eq(entorn));
		
		return diagnosticRepository.findAll(predicate);
    }
	
	public Diagnostic save (Diagnostic diagnostic, Long expedient, Long entorn) {
		Expedient exp = expedientService.findById(expedient);
		
		Entorn ent = entornService.findById(entorn);
		
		diagnostic.setExpedient(exp);
		diagnostic.setEntorn(ent);
		
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

}

