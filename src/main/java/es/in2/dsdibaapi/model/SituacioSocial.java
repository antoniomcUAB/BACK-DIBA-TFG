package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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

	@Id
	private long ID;
	private String SOCIAL;
	private String DEFINICIO;
	
	@ManyToOne
    @JoinColumn(name="ambit",foreignKey= @ForeignKey(name = "SITUACIO_SOCIAL_AMBIT_FK"))
	@JsonIgnore
    private Ambit ambit;
	
	@OneToMany(mappedBy = "situacioSocial")
	@JsonProperty("Items")
    private List<FrequenciaGravetat> frequenciaGravetat;
	
}