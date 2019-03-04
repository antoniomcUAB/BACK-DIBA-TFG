package es.in2.dsdibaapi.service;

import java.util.List;

import es.in2.dsdibaapi.model.Frequencia;

public interface FrequenciaService {
	
	public enum Tipus {
	    CONTINUA,
	    OCASIONAL, 
	    PUNTUAL,
	    SENSE_VALORACIO {
	        @Override
	        public String toString() {
	          return "Sense Valoració";
	        }
	      },
	    FREQUENT {
	        @Override
	        public String toString() {
	          return "Freqüent";
	        }
	      },
	    ALTA
	}
	
    public List<Frequencia> findAll();
	
	public Frequencia findById(Long id) ;	
	
	public Frequencia findByDescription(String description);

	public Frequencia save(Frequencia frequencia);

}
