package es.in2.dsdibaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.repository.SituacioSocialRepository;

@Service
public class SituacioSocialService {
	
	@Autowired
	SituacioSocialService() {}
	
	@Autowired
	SituacioSocialRepository situacioSocialRepository;
	
	@Cacheable("situacio")
    public SituacioSocial findById(Long id) {
		return situacioSocialRepository.findById(id).get();
    }
	
	public SituacioSocial save(SituacioSocial s) {
		return situacioSocialRepository.save(s);
    }
	
}
