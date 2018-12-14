package es.in2.dsdibaapi.model;

import java.util.List;

import lombok.Data;

public @Data class Model {
	
	private List<Ambit> ambits;

	private List<FactorEconomic> factorEconomic;	
}
