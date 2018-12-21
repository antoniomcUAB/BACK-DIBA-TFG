package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@Table (name="FREQUENCIA_GRAVETAT")
@JsonPropertyOrder({ "id", "evidencia", "gravetat","Freqüencia" })
public @Data class FrequenciaGravetat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	@JsonProperty("id")
	private long ID;
	
	@JsonProperty("evidencia")
	private String EVIDENCIA;
	
	@OneToOne
	@JoinColumn(name="gravetat",foreignKey= @ForeignKey(name = "FREQ_GRAVETAT_FK"))
    private Gravetat gravetat;
	
	@ManyToOne
    @JoinColumn(name="situacioSocial",foreignKey= @ForeignKey(name = "FREQ_SITUACIO_SOCIAL_FK"))
	@JsonIgnore
    private SituacioSocial situacioSocial;
	
	@OneToMany(mappedBy = "frequenciaGravetat")
	@JsonProperty("Freqüencia")
    private List<Criteri> criteri;
	
	public FrequenciaGravetat () {
		
	}

	public FrequenciaGravetat (SituacioSocial situacioSocial, String EVIDENCIA, Gravetat gravetat) {
		this.situacioSocial=situacioSocial;
		this.EVIDENCIA = EVIDENCIA;
		this.gravetat=gravetat;
	}

}