package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class ExpedientGlobal implements Serializable {
	
	public enum Estat {
	    INCOMPLET,
	    COMPLET 
	}
	
	private String codi;
	private Date dataCreacio;
	private Date dataValidacio;
	private ExpedientGlobal.Estat estat;
	private Professional professional;
	
	
}
