package es.in2.dsdibaapi.valoracio.ambit;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.service.RiscService;

public class AmbitEvalFactory {
	
	public static RiscService.Tipus getEval (Pregunta p, Ambit a) {
		if (a.getDescripcio().toLowerCase().contains("autonomia")) {
			return new AutonomiaEval (p).avaluar();
		}
		
		if (a.getDescripcio().toLowerCase().contains("material")) {
			return new MaterialEval (p).avaluar();
		}
		
		if (a.getDescripcio().toLowerCase().contains("relacional")) {
			return new RelacionalEval (p).avaluar();
		}
		
		return null;
	}
}
