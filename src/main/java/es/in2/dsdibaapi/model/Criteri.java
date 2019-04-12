package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@DynamicUpdate
@Table (name="DIBA_CRI_CRITERI")
@JsonPropertyOrder({ "id", "evidencia", "frequencia","risc"})

public @Data class Criteri implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "DSDIBA.HIBERNATE_SEQUENCE") 
	@JsonProperty("id")
	@Column (name="DIBA_CRI_ID")
	private long id;
	
	@Column (name="DIBA_CRI_EVIDENCIA")
	private String evidencia;	
	
	@OneToOne
	@JoinColumn(name = "DIBA_CRI_FREQUENCIA",foreignKey= @ForeignKey(name = "DIBA_CRI_CRITERI_FK_FRQ"))
	@JsonIgnoreProperties(value = { "value"} )
	private Frequencia frequencia;
	
	@OneToOne
	@JoinColumn(name = "DIBA_CRI_RISC",foreignKey= @ForeignKey(name = "DIBA_CRI_CRITERI_FK_RIS"))
	@JsonIgnoreProperties(value = { "value"} )
	private Risc risc;
	
	@ManyToOne
    @JoinColumn(name="DIBA_CRI_FREQUENCIA_GRAVETAT",foreignKey= @ForeignKey(name = "DIBA_CRI_CRITERI_FK_FRG"))
	@JsonIgnore
	private FrequenciaGravetat frequenciaGravetat;

	public Criteri () {
		
	}

	public Criteri (String evidencia,Frequencia frequencia,Risc risc,FrequenciaGravetat frequenciaGravetat) {
		this.evidencia=evidencia;
		this.frequencia=frequencia;
		this.risc=risc;
		this.frequenciaGravetat=frequenciaGravetat;
	}
}