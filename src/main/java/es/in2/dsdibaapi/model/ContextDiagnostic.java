package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="CONTEXT_DIAGNOSTIC")
public @Data class ContextDiagnostic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String CODI_CONTEXT;
	private String MEMBRE_UNIC;
	private String MES_UC;
	
	/*
	@OneToOne(mappedBy = "expedient")
    private Expedient expedient;*/
	
	@OneToOne(mappedBy = "persona")
    private Persona persona;
	
	@OneToOne(mappedBy = "questio")
    private Questio questio;
}