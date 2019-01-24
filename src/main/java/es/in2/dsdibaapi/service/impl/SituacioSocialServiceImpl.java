package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.repository.SituacioSocialRepository;
import es.in2.dsdibaapi.service.SituacioSocialService;

@Service
public class SituacioSocialServiceImpl implements SituacioSocialService{
	
	@Autowired
	SituacioSocialServiceImpl() {}
	
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
