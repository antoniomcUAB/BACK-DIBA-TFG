package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Gravetat;

public interface GravetatService {
	
	public enum Tipus {
	    BAIXA,
	    MODERADA, 
	    ALTA
	}
	
    public Gravetat findById(Long id) ;
	
    public List<Gravetat> findAll();

    public Gravetat findByDescription(String description);
	
	public Gravetat save(Gravetat gravetat) ;

}
