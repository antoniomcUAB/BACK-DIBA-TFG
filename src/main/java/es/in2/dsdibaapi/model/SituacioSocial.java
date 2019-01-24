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
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@DynamicUpdate
@Table (name="SITUACIO_SOCIAL")
@JsonPropertyOrder({ "ID", "SOCIAL", "DEFINICIO","items" })
public @Data class SituacioSocial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private Long ID;
	private String SOCIAL;
	private String DEFINICIO;
	private Double vulnerabilitat;
	private Double risc;
	private Double altRisc;
	
	
	@ManyToOne
    @JoinColumn(name="entorn",foreignKey= @ForeignKey(name = "SITUACIO_SOCIAL_ENTORN_FK"))
	@JsonIgnore
    private Entorn entorn;
	
	@ManyToOne
    @JoinColumn(name="versioModel",foreignKey= @ForeignKey(name = "SITUACIO_SOCIAL_VERSIO_FK"))
	@JsonIgnore
    private VersioModel versioModel;
	
	@OneToMany(mappedBy = "situacioSocial")
	@JsonProperty("Items")
    private List<FrequenciaGravetat> frequenciaGravetat;
	
	public SituacioSocial () {
		
	}

	public SituacioSocial (VersioModel versioModel,String SOCIAL,String DEFINICIO, Entorn ENTORN, Double vulnerabilitat, Double risc, Double altRisc) {
		this.SOCIAL=SOCIAL;
		this.DEFINICIO=DEFINICIO;
		this.entorn = ENTORN;		
		this.vulnerabilitat=vulnerabilitat;
		this.risc=risc;
		this.altRisc=altRisc;
		this.versioModel=versioModel;
	}
	
}