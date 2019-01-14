package es.in2.dsdibaapi.core;

import org.springframework.core.env.Environment;

import es.in2.dsdibaapi.model.Diagnostic;

public class Evaluation {
	
	
	
	public static Diagnostic eval (Diagnostic d, Environment env) {
		
		if ( d.getGravetat() == null || d.getFrequencia() == null ||
				(d.getFrequencia().getDESCRIPCIO().equalsIgnoreCase("sense valoració") &&
						d.getGravetat().getDESCRIPCIO().equalsIgnoreCase("alta"))) {
			d.setFactor("Alt Risc");
		}
		else if (d.getFrequencia().getDESCRIPCIO().equalsIgnoreCase("sense valoració")) {
			if(d.getGravetat().getDESCRIPCIO().equalsIgnoreCase("moderada")) {
				d.setFactor("Risc");
			} else {
				d.setFactor("Vulnerabilitat");
			}
		}
		else {
			int gravetat = Integer.valueOf(env.getProperty("eval.gravetat."+d.getGravetat().getDESCRIPCIO().toLowerCase()));
			int frequencia = Integer.valueOf(env.getProperty("eval.frequencia."+d.getFrequencia().getDESCRIPCIO().toLowerCase()));
			
			if ((gravetat*frequencia) <= 1) {
				d.setFactor("Vulnerabilitat");
			}
			else if ((gravetat*frequencia) <= 3) {
				d.setFactor("Risc");
			}
			else {
				d.setFactor("Alt Risc");
			}
		}	
		
		return d;
	}
	
}
