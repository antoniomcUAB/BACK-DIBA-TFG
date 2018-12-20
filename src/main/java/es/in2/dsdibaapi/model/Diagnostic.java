package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="DIAGNOSTIC")
public @Data class Diagnostic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	
	@OneToOne(mappedBy = "expedient")
    private Expedient expedient;
	
	@OneToOne(mappedBy = "modalitatDiagnostic")
    private ModalitatDiagnostic modalitatDiagnostic;
	
	@OneToOne(mappedBy = "questio")
    private Questio questio;
	
	@OneToOne(mappedBy = "gravetat")
    private Gravetat gravetat;
	
	@OneToOne(mappedBy = "tipusPersona")
    private TipusPersona tipusPersona;
	
	@OneToOne(mappedBy = "risc")
    private Risc risc;

}