package es.in2.dsdibaapi.valoracio.ambit;

import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.valoracio.factory.PreguntaEvalFactory;

public class RelacionalEval extends AmbitEval {

	public RelacionalEval(Pregunta p) {
		super(p);
		setVulnerabilitat(2);
		setRisc(4);
		setAltRisc(0);	
	}	

	@Override
	public RiscService.Tipus avaluar() {
		return PreguntaEvalFactory.getEval(this);
	}

}
