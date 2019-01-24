package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Risc;

public interface RiscService {
	
	public enum Tipus {
	    VULNERABILITAT,
	    RISC, 
	    ALT_RISC {
	        @Override
	        public String toString() {
	          return "Alt Risc";
	        }
	      },
	    SENSE_VALORACIO {
		        @Override
		        public String toString() {
		          return "Sense Valoració";
		        }
		      }
	}
	
    public Risc findById(Long id) ;
    
    public List<Risc> findAll();
    
    public Risc findByDescription(Tipus tipus);
	
	public Risc save(Risc risc);

}
