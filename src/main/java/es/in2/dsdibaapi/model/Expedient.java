package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonAlias;
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

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String expedient;
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
    private Set<Persona> persona;
	
	@ManyToOne
    @JoinColumn(name="versioModel",foreignKey= @ForeignKey(name = "EXPEDIENT_VERSIO_MODEL_FK"))	
    private VersioModel versioModel;
	
	@ManyToOne (cascade=CascadeType.MERGE)
    @JoinColumn(name="professional",foreignKey= @ForeignKey(name = "EXPEDIENT_PROFESSIONAL_FK"))	
    private Professional professional;
		
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, mappedBy = "expedient", fetch = FetchType.EAGER)	
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonProperty("preguntes")
    private List<Diagnostic> diagnostic;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, mappedBy = "expedient", fetch = FetchType.EAGER)	
	@Fetch(value = FetchMode.SUBSELECT)
    private List<Contextualitzacio> contextualitzacio;
	
	
	
	@OneToOne
    @JoinColumn(name="valoracio",foreignKey= @ForeignKey(name = "EXPEDIENT_VALORACIO_FK"))	
    private Valoracio valoracio;
	
}