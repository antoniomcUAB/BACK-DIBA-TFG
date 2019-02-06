package es.in2.dsdibaapi.json;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.json.Entorn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Ambit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;
	private String descripcio;
		
	private List<Entorn> entorn;
		
	private Iterable<Contextualitzacio> contextualitzacio;
	
}