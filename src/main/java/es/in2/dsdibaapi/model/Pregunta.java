package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
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
@Table (name="DIBA_PRE_PREGUNTA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Pregunta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "DSDIBA.HIBERNATE_SEQUENCE")
	@Column(name = "DIBA_PRE_ID")
	private Long id;
	@Column(name = "DIBA_PRE_UNITAT_FAMILIAR")
	private Boolean unitatFamiliar;	
	
	@ManyToOne 
	@JoinColumn(name="DIBA_PRE_DIAGNOSTIC", foreignKey= @ForeignKey(name = "DIBA_PRE_PREGUNTA_FK_DGC"),updatable=false)	
	@JsonIgnore	
	private Diagnostic diagnostic;
	
	@ManyToOne
    @JoinColumn(name="DIBA_PRE_SITUACIO_SOCIAL",foreignKey= @ForeignKey(name = "DIBA_PRE_PREGUNTA_FK_SSO"),updatable=false)
	@JsonIgnoreProperties(value = { "definicio", "selectors", "vulnerabilitat", "risc", "altRisc" })
	@QueryInit("entorn.ambit")
	private SituacioSocial situacioSocial;
	
	@ManyToOne
    @JoinColumn(name="DIBA_PRE_GRAVETAT",foreignKey= @ForeignKey(name = "DIBA_PRE_PREGUNTA_FK_GRA"))
	private Gravetat gravetat;
	
	@ManyToOne
    @JoinColumn(name="DIBA_PRE_FREQUENCIA",foreignKey= @ForeignKey(name = "DIBA_PRE_PREGUNTA_FK_FRQ"))	
	private Frequencia frequencia;
	
	@ManyToOne
    @JoinColumn(name="DIBA_PRE_PERSONA",foreignKey= @ForeignKey(name = "DIBA_PRE_PREGUNTA_FK_PER"))	
	private Persona persona;
	
	@ManyToOne
    @JoinColumn(name="DIBA_PRE_FACTOR",foreignKey= @ForeignKey(name = "DIBA_PRE_PREGUNTA_FK_FACTOR"))		
	@JsonIgnoreProperties(value = { "value"})
	private Risc factor;
	
	@ManyToMany(cascade = { CascadeType.MERGE} )
  	 @JoinTable(
       name = "DIBA_ECO_ECONOMIA", 
       joinColumns = { @JoinColumn(name = "DIBA_ECO_PREGUNTA",foreignKey = @ForeignKey(name = "ECONOMIA_PREGUNTA_FK")) },         
       inverseJoinColumns = { @JoinColumn(name = "DIBA_ECO_FACTOR",foreignKey = @ForeignKey(name = "ECONOMIA_FACTOR_ECONOMIC_FK")) }
   )	
   private Set<FactorEconomic> factorEconomic;
	
	public Pregunta (Pregunta d) {
		
	}
	
}