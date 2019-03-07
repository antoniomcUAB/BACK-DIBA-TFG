package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.json.EntornJson;
import es.in2.dsdibaapi.model.AmbitDiagnostic;
import es.in2.dsdibaapi.model.Diagnostic;

public interface DiagnosticService {
	
	public enum Estat {
	    BORRADOR,
	    VALIDAT 
	}
	
	public void deletePreguntasByAmbit (Long diagnostic, Long ambit);
	
	public Diagnostic findById(Long id);	
	
	public Iterable<Diagnostic> findByExpedient(String expedient);
	
	public Diagnostic save(Diagnostic expedient);
	
	public Diagnostic avaluar (Diagnostic diag);
	
	public RiscService.Tipus avaluar (Diagnostic diagnostic,AmbitDiagnostic a, EntornJson e);
	
}
