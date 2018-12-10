package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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

	@Id
	@JsonProperty("id")
	private long ID;
	
	@Column (name="evidencia")
	private String evidencia;	
	
	@OneToOne(mappedBy = "frequencia")
    private Frequencia frequencia;
	
	@OneToOne(mappedBy = "risc")
    private Risc risc;
	
	@ManyToOne
    @JoinColumn(name="frequencia_gravetat",foreignKey= @ForeignKey(name = "CRITERI_FREQUENCIA_GRAVETAT_FK"))
	@JsonIgnore
    private FrequenciaGravetat frequenciaGravetat;

}