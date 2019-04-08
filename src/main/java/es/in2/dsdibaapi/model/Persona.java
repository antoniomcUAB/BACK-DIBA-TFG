package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
@Table (name="DIBA_PER_PERSONA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	@Column(name = "DIBA_PER_ID")
	private long id;
	@Column(name = "DIBA_PER_SEXE")
	private String sexe;
	@Column(name = "DIBA_PER_DATA_NAIXEMENT")
	private Date dataNaixement;
	@Column(name = "DIBA_PER_DATA_ALTA")
	private Date dataAlta;
	@Column(name = "DIBA_PER_DATA_BAIXA")
	private Date dataBaixa;
	@Column(name = "DIBA_PER_REFERENCIA")
	private Boolean referencia;
	
	
	@ManyToOne
    @JoinColumn(name="DIBA_PER_TIPUS_PERSONA",foreignKey= @ForeignKey(name = "DIBA_PER_PERSONA_FK_TPE"))
	private TipusPersona tipusPersona;
	
}