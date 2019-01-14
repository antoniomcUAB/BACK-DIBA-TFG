package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="FREQUENCIA")
public @Data class Frequencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String DESCRIPCIO;
	
	
	public Frequencia () {
		
	}

	public Frequencia (String DESCRIPCIO) {
		this.DESCRIPCIO=DESCRIPCIO;
	}
	
}