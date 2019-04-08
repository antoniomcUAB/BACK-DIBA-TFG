package es.in2.dsdibaapi.valoracio.ambit;

import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.service.RiscService;

public abstract class AmbitEval {	
	
	private Integer vulnerabilitat = 2;
	private Integer risc = 4;
	private Integer altRisc = 5;	
	
	private Pregunta pregunta;
	
	public AmbitEval (Pregunta p) {
		pregunta = p;
	}
		
	public abstract RiscService.Tipus avaluar ();

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	
	
	public Integer getVulnerabilitat() {
		return vulnerabilitat;
	}


	public void setVulnerabilitat(Integer vulnerabilitat) {
		this.vulnerabilitat = vulnerabilitat;
	}


	public Integer getRisc() {
		return risc;
	}


	public void setRisc(Integer risc) {
		this.risc = risc;
	}


	public Integer getAltRisc() {
		return altRisc;
	}

	public void setAltRisc(Integer altRisc) {
		this.altRisc = altRisc;
	}

}
