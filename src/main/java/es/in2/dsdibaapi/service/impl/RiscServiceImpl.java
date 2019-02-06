package es.in2.dsdibaapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import es.in2.dsdibaapi.model.QRisc;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.repository.RiscRepository;
import es.in2.dsdibaapi.service.RiscService;

@Service
public class RiscServiceImpl implements RiscService{
	
	
	
	@Autowired
	RiscServiceImpl() {}
	
	@Autowired
	RiscRepository riscRepository;
	
	@Cacheable("risc")
    public Risc findById(Long id) {
		return riscRepository.findById(id).get();
    }
	
	@Cacheable("risc")
    public List<Risc> findAll() {
		return riscRepository.findAll();
    }
	

	@Cacheable("risc")
    public Risc findByDescription(Tipus tipus) {
		
		Predicate predicate = QRisc.risc.descripcio.equalsIgnoreCase(tipus.toString());
		
		return riscRepository.findOne(predicate).get();
    }
	
	public Risc save(Risc risc) {
		return riscRepository.save(risc);
    }

}
