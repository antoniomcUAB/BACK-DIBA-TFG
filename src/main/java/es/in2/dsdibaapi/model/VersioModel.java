package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="VERSIO_MODEL")
public @Data class VersioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private long ID;
	
	private String versio;
	private Date data;
	
	
	public VersioModel () {
		
	}

	public VersioModel (String versio, Date data) {
		this.versio=versio;
		this.data=data;
	}

}
