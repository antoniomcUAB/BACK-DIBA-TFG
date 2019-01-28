package es.in2.dsdibaapi.model;

import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

public @Data class Model {
	
	@JsonIgnoreProperties(value = { "vulnerabilitat", "risc", "valVulnerabilitat", "valRisc", "valAltrisc"})
	@OrderBy("DESCRIPCIO ASC")
	private Iterable<Ambit> ambits;

	@OrderBy("ID ASC")
	private Iterable<FactorEconomic> factorEconomic;	
}
