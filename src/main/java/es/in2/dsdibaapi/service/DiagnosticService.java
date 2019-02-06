package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Diagnostic;

public interface DiagnosticService {
	
	public enum Estat {
	    BORRADOR,
	    VALIDAT 
	}
	
	public Diagnostic findById(Long id);	
	
	public Iterable<Diagnostic> findByExpedient(String expedient);
	
	public Diagnostic save(Diagnostic expedient, Long versio);
	
	public Diagnostic save(Diagnostic expedient);
	
	public Diagnostic avaluar (Long expedient);
	
	public RiscService.Tipus avaluar (Diagnostic exp, Ambit a);
	
}
