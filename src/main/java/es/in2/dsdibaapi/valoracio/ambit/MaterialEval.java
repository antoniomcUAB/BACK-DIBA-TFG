package es.in2.dsdibaapi.valoracio.ambit;

import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.valoracio.factory.PreguntaEvalFactory;

public class MaterialEval extends AmbitEval {

	public MaterialEval(Pregunta p) {
		super(p);
		setVulnerabilitat(2);
		setRisc(4);
		setAltRisc(15);	
	}	

	@Override
	public RiscService.Tipus avaluar() {
		return PreguntaEvalFactory.getEval(this);
	}
	
	

}
