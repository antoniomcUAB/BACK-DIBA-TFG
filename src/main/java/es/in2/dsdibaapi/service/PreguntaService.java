package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.Pregunta;

public interface PreguntaService {
	
    public Pregunta findById(Long id) ;

    public Pregunta findByDiagnosticSituacioSocial(Long diagnostic,Long situacioSocial);
	
    public Iterable<Pregunta> findByDiagnosticEntorn(Long diagnostic,Long entorn) ;
    
    public Boolean existsById(Long id);
	
	public Pregunta save (Pregunta diagnostic, Long expedient) ;
	
}

