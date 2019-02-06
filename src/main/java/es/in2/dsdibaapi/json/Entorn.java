package es.in2.dsdibaapi.json;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import es.in2.dsdibaapi.model.Pregunta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Entorn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;
	private String descripcio;
	
	
	private Iterable<Pregunta> pregunta;
	
	
	
}