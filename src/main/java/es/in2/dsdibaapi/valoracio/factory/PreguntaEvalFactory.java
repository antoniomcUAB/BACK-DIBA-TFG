package es.in2.dsdibaapi.valoracio.factory;

import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.valoracio.ambit.AmbitEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.E3PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.ES4PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.ES5PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.F1PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.F4PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.F5PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.F6PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.F7PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.GenericaPreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.H2PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.H3PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.H4PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.L2PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.S1PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.S3PreguntaEval;
import es.in2.dsdibaapi.valoracio.factory.pregunta.S5PreguntaEval;

public class PreguntaEvalFactory {
	
	public static RiscService.Tipus getEval (AmbitEval a) {
		if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("A.") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("H.1") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("E.2") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("L.1") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("L.2") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("ES.1") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("ES.2") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("ES.3") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.2") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("S.4")) {
			return new GenericaPreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("H.2") ||				
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.5") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.8") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("S.2")) {
			return new H2PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("H.3") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("H.5") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("E.1")) {
			return new H3PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("E.3")) {
			return new E3PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("H.4")) {
			return new H4PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("L.2")) {
			return new L2PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("ES.4")) {
			return new ES4PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("ES.5") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.3")) {
			return new ES5PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.1")) {
			return new F1PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.4")) {
			return new F4PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.5")) {
			return new F5PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.6")) {
			return new F6PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.7") ||
				a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("F.9")) {
			return new F7PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("S.1")) {
			return new S1PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("S.3")) {
			return new S3PreguntaEval().avaluar(a);
		}
		else if (a.getPregunta().getSituacioSocial().getSocial().toUpperCase().contains("S.5")) {
			return new S5PreguntaEval().avaluar(a);
		}
		
		return null;
	}
}
