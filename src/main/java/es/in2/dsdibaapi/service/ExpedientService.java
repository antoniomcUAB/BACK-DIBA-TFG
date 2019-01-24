package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Expedient;

public interface ExpedientService {
	
	public Expedient findById(Long id);	
	
	public Iterable<Expedient> findByExpedient(String expedient);
	
	public Iterable<Expedient> findByMunicipi(Long municipi);
	
	public Expedient save(Expedient expedient, Long versio);
	
	public Expedient save(Expedient expedient);
	
	public Expedient avaluar (Long expedient);
	
	public RiscService.Tipus avaluar (Expedient exp, Ambit a);
	
}
