package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIAGNOSTIC")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Diagnostic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;	
	private Date data;
	private String observacions;
	
	
	
	/*@ManyToOne
    @JoinColumn(name="expedient",foreignKey= @ForeignKey(name = "DIAGNOSTIC_EXPEDIENT_FK"))	
	@JsonIgnoreProperties(value = { "diagnostic", "professional", "persona"} )*/
	@ManyToOne
    @JoinColumn(name="expedient",foreignKey= @ForeignKey(name = "DIAGNOSTIC_EXPEDIENT_FK"))	
	@JsonIgnore
    private Expedient expedient;
	
	@ManyToOne
    @JoinColumn(name="estat",foreignKey= @ForeignKey(name = "DIAGNOSTIC_ESTAT_FK"))	
    private Estat estat;
	
	@ManyToOne
    @JoinColumn(name="versioModel",foreignKey= @ForeignKey(name = "DIAGNOSTIC_VERSIO_MODEL_FK"))	
    private VersioModel versioModel;
	
	/*	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)	
	@JoinColumn (name="diagnostic",referencedColumnName="id")
	@JsonProperty("preguntes")	
    private List<Pregunta> pregunta;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, mappedBy = "diagnostic", fetch = FetchType.EAGER)	
	@Fetch(value = FetchMode.SUBSELECT)
    private List<Contextualitzacio> contextualitzacio;
	*/
	@ManyToOne
    @JoinColumn(name="professional",foreignKey= @ForeignKey(name = "DIAGNOSTIC_PROFESSIONAL_FK"))	
	//@JsonIgnore
    private Professional professional;
	
	
	@Transient
	@JsonIgnoreProperties(value = { "vulnerabilitat", "risc", "valVulnerabilitat", "valRisc", "valAltrisc"})
	private List<es.in2.dsdibaapi.json.AmbitJson> ambit;
	
	
	@OneToOne (cascade= {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
    @JoinColumn(name="valoracio",foreignKey= @ForeignKey(name = "DIAGNOSTIC_VALORACIO_FK"))	
    private Valoracio valoracio;
	
}