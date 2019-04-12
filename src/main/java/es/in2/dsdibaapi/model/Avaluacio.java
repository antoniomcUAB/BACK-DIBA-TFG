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
@Table (name="DIBA_AVL_AVALUACIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Avaluacio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "DSDIBA.HIBERNATE_SEQUENCE")
	@Column(name = "DIBA_AVL_ID")
	private long id;
	@Column(name = "DIBA_AVL_JUSTIFICACIO")
	private String justificacio;
	
	@ManyToOne
    @JoinColumn(name="DIBA_AVL_AMBIT",foreignKey= @ForeignKey(name = "DIBA_AVL_AVALUACIO_FK_AMD"))
	@JsonIgnoreProperties(value = { "diagnostic", "entorn", "contextualitzacio"})
	private AmbitDiagnostic ambit;
	
	
	@ManyToOne 
	@JoinColumn(name="DIBA_AVL_VALORACIO",foreignKey= @ForeignKey(name = "DIBA_AVL_AVALUACIO_FK_VAL"), updatable = false)
	@JsonIgnore
	private Valoracio valoracio;
	
	@ManyToOne
	@JoinColumn(name="DIBA_AVL_RISC",foreignKey= @ForeignKey(name = "DIBA_AVL_AVALUACIO_FK_RIS"))	
    private Risc risc;
	
	@ManyToOne
	@JoinColumn(name="DIBA_AVL_RISC_PROFESSIONAL",foreignKey= @ForeignKey(name = "DIBA_AVL_AVALUACIO_FK_RIS_PROF"))
    private Risc riscProfessional;
	
	

}