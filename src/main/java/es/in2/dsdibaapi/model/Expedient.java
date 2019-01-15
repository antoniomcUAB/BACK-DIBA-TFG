package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table (name="EXPEDIENT")
public @Data class Expedient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String EXPEDIENT;
	private String NOM;
	private Date DATA;
	private String OBSERVACIONS;
	private String estat;
	private Long totalFamilia;
	
	
	@ManyToMany(cascade = { CascadeType.MERGE } )
   	 @JoinTable(
        name = "ex_referencia", 
        joinColumns = { @JoinColumn(name = "expedient",foreignKey = @ForeignKey(name = "EX_REFERENCIA_EXPEDIENT_FK")) },         
        inverseJoinColumns = { @JoinColumn(name = "persona",foreignKey = @ForeignKey(name = "EX_REFERENCIA_PERSONA_FK")) }
    )	
    private Set<Persona> persona = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="versioModel",foreignKey= @ForeignKey(name = "EXPEDIENT_VERSIO_MODEL_FK"))
	@JsonIgnore
    private VersioModel versioModel;
	
	@ManyToOne
    @JoinColumn(name="professional",foreignKey= @ForeignKey(name = "EXPEDIENT_PROFESSIONAL_FK"))
	@JsonIgnore
    private Professional professional;
	
	@OneToMany(mappedBy = "expedient")	
    private List<Diagnostic> diagnostic;
	
	
	public Expedient () {
		
	}

	public Expedient (String EXPEDIENT, 
						Professional professional, 
						String NOM, Date DATA, 
						String OBSERVACIONS,
						Set<Persona> personas, 
						Long totalFamilia,
						VersioModel versioModel,
						String estat) {
		this.EXPEDIENT=EXPEDIENT;
		this.NOM=NOM;
		this.persona=personas;
		this.DATA=DATA;
		this.OBSERVACIONS=OBSERVACIONS;
		this.totalFamilia=totalFamilia;
		this.versioModel=versioModel;
		this.professional=professional;
		this.estat=estat;
	}

	
	
}