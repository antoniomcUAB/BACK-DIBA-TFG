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
	private Long ID;
	private Boolean unitatFamiliar;	
	
	@ManyToOne ()
	@JoinColumn(name="expedient", foreignKey= @ForeignKey(name = "DIAGNOSTICv1_EXPEDIENT_FK"),updatable=false)	
	@JsonIgnore	
    private Expedient expedient;
	
	@ManyToOne
    @JoinColumn(name="entorn",foreignKey= @ForeignKey(name = "DIAGNOSTIC2_ENTORN_FK"),updatable=false)
	@JsonIgnoreProperties(value = { "Items"})
	private Entorn entorn;
	
	@ManyToOne
    @JoinColumn(name="situacio_social",foreignKey= @ForeignKey(name = "DIAGNOSTIC2_SITUACIO_SOCIAL_FK"))
	@JsonIgnoreProperties(value = { "definicio", "Items", "vulnerabilitat", "risc", "altRisc" })
	private SituacioSocial situacioSocial;
	
	@ManyToOne
    @JoinColumn(name="risc",foreignKey= @ForeignKey(name = "DIAGNOSTICv1_RISC_FK"))
	@JsonIgnoreProperties(value = { "value"})
	private Risc risc;
	
	@ManyToOne
    @JoinColumn(name="gravetat",foreignKey= @ForeignKey(name = "DIAGNOSTICv1_GRAVETAT_FK"))
    private Gravetat gravetat;
	
	@ManyToOne
    @JoinColumn(name="frequencia",foreignKey= @ForeignKey(name = "DIAGNOSTICv1_FREQUENCIA_FK"))	
    private Frequencia frequencia;
	
	@ManyToOne
    @JoinColumn(name="persona",foreignKey= @ForeignKey(name = "DIAGNOSTIC2_PERSONA_FK"))	
    private Persona persona;
	
	@ManyToOne
    @JoinColumn(name="factor",foreignKey= @ForeignKey(name = "DIAGNOSTIC_FACTOR_FK"))	
	private Risc factor;
	
	/*
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "diagnostic", fetch = FetchType.EAGER)	
	@Fetch(value = FetchMode.SUBSELECT)
    private List<Economia> economia;*/
	@ManyToMany(cascade = { CascadeType.MERGE } )
  	 @JoinTable(
       name = "economia", 
       joinColumns = { @JoinColumn(name = "diagnostic",foreignKey = @ForeignKey(name = "ECONOMIA_DIAGNOSTIC_FK")) },         
       inverseJoinColumns = { @JoinColumn(name = "factor",foreignKey = @ForeignKey(name = "ECONOMIA_FACTOR_ECONOMIC_FK")) }
   )	
   private Set<FactorEconomic> factorEconomic;
	
	public Diagnostic (Diagnostic d) {
		
	}
	
}