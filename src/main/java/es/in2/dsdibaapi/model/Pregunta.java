package es.in2.dsdibaapi.model;

import java.io.Serializable;
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
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.querydsl.core.annotations.QueryInit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="PREGUNTA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Pregunta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private Long id;
	private Boolean unitatFamiliar;	
	
	@ManyToOne 
	@JoinColumn(name="diagnostic", foreignKey= @ForeignKey(name = "PREGUNTA_PREGUNTA_FK"),updatable=false)	
	@JsonIgnore	
    private Diagnostic diagnostic;
	
	/*
	@ManyToOne
    @JoinColumn(name="entorn",foreignKey= @ForeignKey(name = "PREGUNTA_ENTORN_FK"),updatable=false)
	@JsonIgnoreProperties(value = { "preguntes"})
	private Entorn entorn;
	*/
	
	@ManyToOne
    @JoinColumn(name="situacio_social",foreignKey= @ForeignKey(name = "PREGUNTA_SITUACIO_SOCIAL_FK"))
	@JsonIgnoreProperties(value = { "definicio", "selectors", "vulnerabilitat", "risc", "altRisc" })
	@QueryInit("entorn.ambit")
	private SituacioSocial situacioSocial;
	
	/*
	@ManyToOne
    @JoinColumn(name="risc",foreignKey= @ForeignKey(name = "PREGUNTA_RISC_FK"))
	@JsonIgnore
	private Risc risc;*/
	
	@ManyToOne
    @JoinColumn(name="gravetat",foreignKey= @ForeignKey(name = "PREGUNTA_GRAVETAT_FK"))
    private Gravetat gravetat;
	
	@ManyToOne
    @JoinColumn(name="frequencia",foreignKey= @ForeignKey(name = "PREGUNTA_FREQUENCIA_FK"))	
    private Frequencia frequencia;
	
	@ManyToOne
    @JoinColumn(name="persona",foreignKey= @ForeignKey(name = "PREGUNTA_PERSONA_FK"))	
    private Persona persona;
	
	@ManyToOne
    @JoinColumn(name="factor",foreignKey= @ForeignKey(name = "PREGUNTA_FACTOR_FK"))		
	@JsonIgnoreProperties(value = { "value"})
	private Risc factor;
	
	/*
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "diagnostic", fetch = FetchType.EAGER)	
	@Fetch(value = FetchMode.SUBSELECT)
    private List<Economia> economia;*/
	@ManyToMany(cascade = { CascadeType.MERGE} )
  	 @JoinTable(
       name = "economia", 
       joinColumns = { @JoinColumn(name = "pregunta",foreignKey = @ForeignKey(name = "ECONOMIA_PREGUNTA_FK")) },         
       inverseJoinColumns = { @JoinColumn(name = "factor",foreignKey = @ForeignKey(name = "ECONOMIA_FACTOR_ECONOMIC_FK")) }
   )	
   private Set<FactorEconomic> factorEconomic;
	
	public Pregunta (Pregunta d) {
		
	}
	
}