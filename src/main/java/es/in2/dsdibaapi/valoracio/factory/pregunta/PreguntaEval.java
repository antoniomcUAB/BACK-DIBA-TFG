package es.in2.dsdibaapi.valoracio.factory.pregunta;

import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.valoracio.ambit.AmbitEval;

public abstract class PreguntaEval {	
	
	
	public abstract RiscService.Tipus avaluar (AmbitEval a);

}
