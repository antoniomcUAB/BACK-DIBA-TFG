package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Diagnostic;

public interface DiagnosticService {
	
    public Diagnostic findById(Long id) ;

    public Diagnostic findByExpedientSituacioSocial(Long expedient,Long situacioSocial);
	
    public Iterable<Diagnostic> findByExpedientEntorn(Long expedient,Long entorn) ;
    
    public Boolean existsById(Long id);
	
	public Diagnostic save (Diagnostic diagnostic, Long expedient, Long entorn) ;
	
}

