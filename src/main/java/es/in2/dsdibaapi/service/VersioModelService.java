package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.VersioModel;

public interface VersioModelService {
	
    public VersioModel findById(Long id) ;
    
    public VersioModel findCurrent();
    
    public List<VersioModel> findAll();

	public VersioModel save(VersioModel v);	

}
