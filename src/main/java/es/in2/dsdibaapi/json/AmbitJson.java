package es.in2.dsdibaapi.json;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Factor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class AmbitJson implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;
	private String descripcio;
	private Double vulnerabilitat;
	private Double risc;
	private Double valVulnerabilitat;
	private Double valRisc;
	private Double valAltrisc;
	private String observacions;
	
	private List<EntornJson> entorn;
	
	
	private Iterable<Contextualitzacio> contextualitzacio;
	
}