package es.in2.dsdibaapi.valoracio.pregunta;

import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.RiscService.Tipus;
import es.in2.dsdibaapi.valoracio.ambit.AmbitEval;

public class F1PreguntaEval extends PreguntaEval {

	@Override
	public Tipus avaluar(AmbitEval a) {
		if ( a.getPregunta().getGravetat() == null || a.getPregunta().getFrequencia() == null) {
			return RiscService.Tipus.ALT_RISC;
		}
		else if ( a.getPregunta().getFrequencia().getDescripcio().equalsIgnoreCase("ocasional") 
				&& a.getPregunta().getGravetat().getDescripcio().equalsIgnoreCase("moderada")) {
			return RiscService.Tipus.RISC;
		}
		else if ( a.getPregunta().getFrequencia().getDescripcio().equalsIgnoreCase("continua") 
				&& a.getPregunta().getGravetat().getDescripcio().equalsIgnoreCase("moderada")) {
			return RiscService.Tipus.RISC;
		}	
		else if (a.getPregunta().getFrequencia().getDescripcio().equalsIgnoreCase("ocasional") 
			&& a.getPregunta().getGravetat().getDescripcio().equalsIgnoreCase("alta")) {
				return RiscService.Tipus.VULNERABILITAT;
			
		}	
		else if (a.getPregunta().getFrequencia().getDescripcio().equalsIgnoreCase("sense valoració")) {
			if(a.getPregunta().getGravetat().getDescripcio().equalsIgnoreCase("alta")) {
				return RiscService.Tipus.ALT_RISC;
			} else if(a.getPregunta().getGravetat().getDescripcio().equalsIgnoreCase("moderada")) {
				return RiscService.Tipus.RISC;
			} else {
				return RiscService.Tipus.VULNERABILITAT;
			}
		}		
		else {
			int res = a.getPregunta().getGravetat().getValue()*a.getPregunta().getFrequencia().getValue();
			if (res <= a.getVulnerabilitat()) {
				return RiscService.Tipus.VULNERABILITAT;
			}
			else if (res <= a.getRisc()) {
				return RiscService.Tipus.RISC;
			}			
		}	
		
		return RiscService.Tipus.ALT_RISC;
	}

}
