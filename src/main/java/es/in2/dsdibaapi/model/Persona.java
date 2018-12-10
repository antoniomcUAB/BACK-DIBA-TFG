package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table (name="PERSONA")
public @Data class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long ID;
	private String NOM;
	private String COGNOM1;
	private String COGNOM2;

	
	@OneToOne
    @JoinColumn(name = "id")
	@JsonIgnore
    private Persona persona;
}