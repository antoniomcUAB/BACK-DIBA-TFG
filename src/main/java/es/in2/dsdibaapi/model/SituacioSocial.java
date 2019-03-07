package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@DynamicUpdate
@Table (name="SITUACIO_SOCIAL")
@JsonPropertyOrder({ "id", "social", "definicio","selectors" })
public @Data class SituacioSocial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private Long id;
	private String social;
	private String definicio;
	private Double vulnerabilitat;
	private Double risc;
	private Double altRisc;
	
	
	@ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="entorn",foreignKey= @ForeignKey(name = "SITUACIO_SOCIAL_ENTORN_FK"))
	@JsonIgnore
    private Entorn entorn;
	
	@ManyToOne
    @JoinColumn(name="versioModel",foreignKey= @ForeignKey(name = "SITUACIO_SOCIAL_VERSIO_FK"))
	@JsonIgnore
    private VersioModel versioModel;
	
	@OneToMany(mappedBy = "situacioSocial")
	@JsonProperty("selectors")
    private List<FrequenciaGravetat> frequenciaGravetat;
	
	public SituacioSocial () {
		
	}

	public SituacioSocial (VersioModel versioModel,String social,String definicio, Entorn entorn, Double vulnerabilitat, Double risc, Double altRisc) {
		this.social=social;
		this.definicio=definicio;
		this.entorn = entorn;		
		this.vulnerabilitat=vulnerabilitat;
		this.risc=risc;
		this.altRisc=altRisc;
		this.versioModel=versioModel;
	}
	
}