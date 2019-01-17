package es.in2.dsdibaapi.core;

import org.springframework.core.env.Environment;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.service.RiscService;

public class Evaluation {
	/*
	public static RiscService.Tipus evalRisc (Diagnostic d, Environment env) {	
		
		
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
	*/
	
	
}
