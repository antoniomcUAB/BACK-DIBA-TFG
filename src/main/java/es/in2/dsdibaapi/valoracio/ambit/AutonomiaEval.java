package es.in2.dsdibaapi.valoracio.ambit;

import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.valoracio.pregunta.PreguntaEvalFactory;

public class AutonomiaEval extends AmbitEval {
	
	public AutonomiaEval(Pregunta p) {
		super(p);
		setVulnerabilitat(4);
		setRisc(7);
		setAltRisc(0);	
	}	

	@Override
	public RiscService.Tipus avaluar() {
		return PreguntaEvalFactory.getEval(this);
	}
}
