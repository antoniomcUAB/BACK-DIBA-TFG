package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@Table (name="AMBIT")
@JsonPropertyOrder({ "ID", "DESCRIPCIO", "items" })
public @Data class Ambit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long ID;
	private String DESCRIPCIO;
	
	
	@OneToMany(mappedBy = "ambit")
	@JsonProperty("Items")
    private List<SituacioSocial> situacioSocial;
	
	@OneToMany(mappedBy = "ambit")
	@JsonProperty("Factors_context")
    private List<FactorGravetat> factorGravetat;
	
	
}