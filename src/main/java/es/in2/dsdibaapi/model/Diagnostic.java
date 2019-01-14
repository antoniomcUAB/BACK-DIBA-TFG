package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table (name="DIAGNOSTIC")

public @Data class Diagnostic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String factor;
	private Boolean unitatFamiliar;	
	
	@ManyToOne
	@JoinColumn(name="expedient",foreignKey= @ForeignKey(name = "DIAGNOSTICv1_EXPEDIENT_FK"))
	@JsonIgnore
    private Expedient expedient;
	
	@ManyToOne
    @JoinColumn(name="entorn",foreignKey= @ForeignKey(name = "DIAGNOSTIC2_ENTORN_FK"))
	@JsonIgnore
	private Entorn entorn;
	
	@ManyToOne
    @JoinColumn(name="situacio_social",foreignKey= @ForeignKey(name = "DIAGNOSTIC2_SITUACIO_SOCIAL_FK"))
	@JsonIgnoreProperties(value = { "definicio", "Items", "vulnerabilitat", "risc", "altRisc" })
	private SituacioSocial situacioSocial;
	
	@ManyToOne
    @JoinColumn(name="risc",foreignKey= @ForeignKey(name = "DIAGNOSTICv1_RISC_FK"))
	private Risc risc;
	
	@ManyToOne
    @JoinColumn(name="gravetat",foreignKey= @ForeignKey(name = "DIAGNOSTICv1_GRAVETAT_FK"))
    private Gravetat gravetat;
	
	@ManyToOne
    @JoinColumn(name="frequencia",foreignKey= @ForeignKey(name = "DIAGNOSTICv1_FREQUENCIA_FK"))	
    private Frequencia frequencia;
	
	@ManyToOne
    @JoinColumn(name="persona",foreignKey= @ForeignKey(name = "DIAGNOSTIC2_PERSONA_FK"))	
    private Persona persona;
	
	
	
	public Diagnostic () {
		
	}
	
	public Diagnostic (Expedient expedient,Entorn entorn,SituacioSocial situacioSocial,Risc risc,Frequencia frequencia,Gravetat gravetat,Boolean unitatFamiliar) {
		this.expedient = expedient;
		this.entorn = entorn;
		this.situacioSocial = situacioSocial;
		this.frequencia = frequencia;
		this.gravetat = gravetat;
		this.risc = risc;
		this.unitatFamiliar = unitatFamiliar;
	}
	
	
}