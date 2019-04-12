package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
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
@Table (name="DIBA_FRG_FREQUENCIAGRAVETAT")
@JsonPropertyOrder({ "id", "evidencia", "gravetat","frequencia" })
public @Data class FrequenciaGravetat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "DSDIBA.HIBERNATE_SEQUENCE") 
	@JsonProperty("id")
	@Column (name="DIBA_FRG_ID")
	private long id;
	
	@JsonProperty("evidencia")
	@Column (name="DIBA_FRG_EVIDENCIA")
	private String evidencia;
	
	@OneToOne
	@JoinColumn(name="DIBA_FRG_GRAVETAT",foreignKey= @ForeignKey(name = "DIBA_FRG_FK_GRA"))
	@JsonIgnoreProperties(value = { "value"} )
	private Gravetat gravetat;
	
	@ManyToOne
    @JoinColumn(name="DIBA_FRG_SITUACIO_SOCIAL",foreignKey= @ForeignKey(name = "DIBA_FRG_FK_SSO"))
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