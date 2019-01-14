package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="RISC")
public @Data class Risc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private long ID;
	
	private String DESCRIPCIO;
	
	
	public Risc () {
		
	}

	public Risc (String DESCRIPCIO) {
		this.DESCRIPCIO=DESCRIPCIO;
	}

}