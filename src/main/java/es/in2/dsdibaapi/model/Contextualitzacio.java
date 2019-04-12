package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_CTX_CONTEXTUALITZACIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Contextualitzacio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "DSDIBA.HIBERNATE_SEQUENCE")
	@Column(name = "DIBA_CTX_ID")
	private long id;
	@Column(name = "DIBA_CTX_MEMBRE_UNIC")
	private Boolean membreUnic;
	@Column(name = "DIBA_CTX_MES_UC")
	private Boolean mesUc;
	
	@ManyToOne
    @JoinColumn(name="DIBA_CTX_FACTOR",foreignKey= @ForeignKey(name = "DIBA_CTX_FK_FCT"))
	@JsonIgnoreProperties(value = { "fc1m", "fctots", "ambit"})
	private Factor factor;
	
	@ManyToOne
    @JoinColumn(name="DIBA_CTX_PERSONA",foreignKey= @ForeignKey(name = "DIBA_CTX_FK_PER"))
	private Persona persona;
	
	@ManyToOne 
    @JoinColumn(name="DIBA_CTX_DIAGNOSTIC",foreignKey= @ForeignKey(name = "DIBA_CTX_FK_DGC"),updatable=false)
	@JsonIgnore
	private Diagnostic diagnostic;
	

}