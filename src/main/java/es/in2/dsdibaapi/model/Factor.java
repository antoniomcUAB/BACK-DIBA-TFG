package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table (name="FACTOR")
public @Data class Factor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String DESCRIPCIO;
	
	@OneToOne
    @JoinColumn(name = "id")
	@JsonIgnore
    private Factor factor;
	
	public Factor () {
		
	}

	public Factor (String DESCRIPCIO) {
		this.DESCRIPCIO=DESCRIPCIO;
	}
}