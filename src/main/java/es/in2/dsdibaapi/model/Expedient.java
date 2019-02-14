package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="EXPEDIENT")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Expedient implements Serializable {
	
	
	@Id @GeneratedValue 
	private long id;
	private String codi;
	private Date dataCreacio;
	private Date dataValidacio;
	private Long totalFamilia;
	private String observacions;
	
	/*
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)	
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonProperty("persona")*/
	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name="expedient",referencedColumnName="id")
	@JsonProperty("persona")
	private Set<Persona> persona;
	
	@ManyToOne
    @JoinColumn(name="estat",foreignKey= @ForeignKey(name = "EXPEDIENT_ESTAT_FK"))	
    private Estat estat;
	

	@ManyToOne
    @JoinColumn(name="professional",foreignKey= @ForeignKey(name = "EXPEDIENT_PROFESSIONAL_FKv2"))	
	//@JsonIgnore
    private Professional professional;
	
	/*@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, mappedBy = "expedient", fetch = FetchType.EAGER)	
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnoreProperties(value = { "expedient"} )
	@JsonProperty("diagnostic")*/
	@OneToMany(cascade=CascadeType.MERGE)
	@JoinColumn(name="expedient")
    private List<Diagnostic> diagnostic;
	
}
