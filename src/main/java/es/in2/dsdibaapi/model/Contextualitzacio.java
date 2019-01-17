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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="CONTEXTUALITZACIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Contextualitzacio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private Boolean membreUnic;
	private Boolean mesUc;
	
	@ManyToOne
    @JoinColumn(name="factor",foreignKey= @ForeignKey(name = "CONTX_FACTOR_FK"))	
    private Factor factor;
	
	@ManyToOne
    @JoinColumn(name="persona",foreignKey= @ForeignKey(name = "CONTX_PERSONA_FK"))	
    private Persona persona;
	
	@ManyToOne
    @JoinColumn(name="expedient",foreignKey= @ForeignKey(name = "CONTX_EXPEDIENT_FK"))
	@JsonIgnore
    private Expedient expedient;
	

}