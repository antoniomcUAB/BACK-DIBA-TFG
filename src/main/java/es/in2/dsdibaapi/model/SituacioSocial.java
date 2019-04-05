package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
@Table (name="DIBA_SSO_SITUACIO_SOCIAL")
@JsonPropertyOrder({ "id", "social", "definicio","selectors" })
public @Data class SituacioSocial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	@Column(name = "DIBA_SSO_ID")
	private Long id;
	@Column(name = "DIBA_SSO_SOCIAL")
	private String social;
	@Column(name = "DIBA_SSO_DEFINICIO")
	private String definicio;
	@Column(name = "DIBA_SSO_VULNERABILITAT")
	private Double vulnerabilitat;
	@Column(name = "DIBA_SSO_RISC")
	private Double risc;
	@Column(name = "DIBA_SSO_ALT_RISC")
	private Double altRisc;
	
	
	@ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="DIBA_SSO_ENTORN",foreignKey= @ForeignKey(name = "DIBA_SSO_FK_ENT"))
	@JsonIgnore
    private Entorn entorn;
	
	@ManyToOne
    @JoinColumn(name="DIBA_SSO_VERSIO_MODEL",foreignKey= @ForeignKey(name = "DIBA_SSO_FK_VSM"))
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