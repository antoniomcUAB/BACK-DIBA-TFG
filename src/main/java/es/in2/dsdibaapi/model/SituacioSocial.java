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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@Table (name="SITUACIO_SOCIAL")
@JsonPropertyOrder({ "ID", "SOCIAL", "DEFINICIO","items" })
public @Data class SituacioSocial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String SOCIAL;
	private String DEFINICIO;
	
	@ManyToOne
    @JoinColumn(name="entorn",foreignKey= @ForeignKey(name = "SITUACIO_SOCIAL_ENTORN_FK"))
	@JsonIgnore
    private Entorn entorn;
	
	@OneToMany(mappedBy = "situacioSocial")
	@JsonProperty("Items")
    private List<FrequenciaGravetat> frequenciaGravetat;
	
	public SituacioSocial () {
		
	}

	public SituacioSocial (String SOCIAL,String DEFINICIO, Entorn ENTORN) {
		this.SOCIAL=SOCIAL;
		this.DEFINICIO=DEFINICIO;
		this.entorn = ENTORN;
	}
	
}