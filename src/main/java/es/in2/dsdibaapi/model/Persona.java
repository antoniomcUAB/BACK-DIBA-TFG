package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
/*
	@ManyToOne
    @JoinColumn(name="exreferencia",foreignKey= @ForeignKey(name = "ENTORN_AMBIT_FK"))
	@JsonIgnore
    private ExReferencia exReferencia;*/
	
	@OneToOne
    @JoinColumn(name = "id")
	@JsonIgnore
    private Persona persona;
	
	public Persona () {
		
	}

	public Persona (String NOM,String COGNOM1,String COGNOM2) {
		this.NOM=NOM;
		this.COGNOM1=COGNOM1;
		this.COGNOM2=COGNOM2;
	}
}