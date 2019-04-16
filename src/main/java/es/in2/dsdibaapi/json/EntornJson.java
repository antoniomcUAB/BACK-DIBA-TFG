package es.in2.dsdibaapi.json;

import java.io.Serializable;

import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
import javax.persistence.Id;

import es.in2.dsdibaapi.model.Pregunta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class EntornJson implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue //(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE") 
	private long id;
	private String descripcio;
	
	
	private Iterable<Pregunta> pregunta;
	
	
	
}