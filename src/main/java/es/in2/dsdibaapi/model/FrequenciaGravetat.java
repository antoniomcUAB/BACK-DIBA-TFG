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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@DynamicUpdate
@Table (name="FREQUENCIA_GRAVETAT")
@JsonPropertyOrder({ "id", "evidencia", "gravetat","frequencia" })
/*@AllArgsConstructor
@NoArgsConstructor
@Builder*/
public @Data class FrequenciaGravetat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("evidencia")
	private String evidencia;
	
	@OneToOne
	@JoinColumn(name="gravetat",foreignKey= @ForeignKey(name = "FREQ_GRAVETAT_FK"))
	@JsonIgnoreProperties(value = { "value"} )
    private Gravetat gravetat;
	
	@ManyToOne
    @JoinColumn(name="situacioSocial",foreignKey= @ForeignKey(name = "FREQ_SITUACIO_SOCIAL_FK"))
	@JsonIgnore
    private SituacioSocial situacioSocial;
	
	@OneToMany(mappedBy = "frequenciaGravetat")
	@JsonProperty("frequencia")
    private List<Criteri> criteri;
	
	public FrequenciaGravetat () {
		
	}

	public FrequenciaGravetat (SituacioSocial situacioSocial, String evidencia, Gravetat gravetat) {
		this.situacioSocial=situacioSocial;
		this.evidencia = evidencia;
		this.gravetat=gravetat;
	}

}