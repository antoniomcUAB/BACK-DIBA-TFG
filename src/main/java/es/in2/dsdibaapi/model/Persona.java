package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="PERSONA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;
	private String sexe;
	private Date dataNaixement;
	private Date dataAlta;
	private Date dataBaixa;
	private Boolean referencia;
	
	@ManyToOne
    @JoinColumn(name="tipusPersona",foreignKey= @ForeignKey(name = "PERSONA_TIPUS_PERSONA_FK"))	
    private TipusPersona tipusPersona;

	
}