package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.Estat;
import es.in2.dsdibaapi.model.QEstat;
import es.in2.dsdibaapi.repository.EstatRepository;
import es.in2.dsdibaapi.service.EstatService;

@Service
public class EstatServiceImpl implements EstatService{
	
	@Autowired
	EstatServiceImpl() {}
	
	@Autowired
	EstatRepository estatRepository;
	
	@Cacheable("estat")
    public Estat findById(Long id) {
		return estatRepository.findById(id).get();
    }
	
	@Cacheable("estat")
    public Estat findByDescripcio(String descripcio) {
		Predicate predicate = QEstat.estat.descripcio.equalsIgnoreCase(descripcio);

		return estatRepository.findOne(predicate).get();
    }
	

	public Estat save(Estat v) {
		return estatRepository.save(v);
    }



}
