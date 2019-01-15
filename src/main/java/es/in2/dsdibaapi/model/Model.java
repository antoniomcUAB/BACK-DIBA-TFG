package es.in2.dsdibaapi.model;

import lombok.Data;

public @Data class Model {
	
	private Iterable<Ambit> ambits;

	private Iterable<FactorEconomic> factorEconomic;	
}
