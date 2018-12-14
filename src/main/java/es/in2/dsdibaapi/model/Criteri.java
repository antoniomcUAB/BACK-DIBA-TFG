package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@Table (name="CRITERI")
@JsonPropertyOrder({ "id", "evidencia", "frequencia","risc"})
public @Data class Criteri implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	@JsonProperty("id")
	private long ID;
	
	@Column (name="evidencia")
	private String evidencia;	
	
	@OneToOne
	@JoinColumn(name = "frequencia",foreignKey= @ForeignKey(name = "CRITERI_FREQUENCIA_FK"))
    private Frequencia frequencia;
	
	@OneToOne
	@JoinColumn(name = "risc",foreignKey= @ForeignKey(name = "CRITERI_RISC_FK"))
    private Risc risc;
	
	@ManyToOne
    @JoinColumn(name="frequencia_gravetat",foreignKey= @ForeignKey(name = "CRITERI_FREQUENCIA_GRAVETAT_FK"))
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