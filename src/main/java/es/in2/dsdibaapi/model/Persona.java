package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="PERSONA")
public @Data class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String NOM;
	private String COGNOM1;
	private String COGNOM2;

	
	public Persona () {
		
	}

	public Persona (String NOM,String COGNOM1,String COGNOM2) {
		this.NOM=NOM;
		this.COGNOM1=COGNOM1;
		this.COGNOM2=COGNOM2;
	}
}