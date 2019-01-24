package es.in2.dsdibaapi.service;

import es.in2.dsdibaapi.model.SituacioSocial;

public interface SituacioSocialService {
	
    public SituacioSocial findById(Long id) ;
	
	public SituacioSocial save(SituacioSocial s);
	
}
