package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table (name="GRAVETAT")
public @Data class Gravetat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String DESCRIPCIO;
	
	@OneToOne
    @JoinColumn(name = "id")
	@JsonIgnore
    private Gravetat gravetat;
	

	public Gravetat () {
		
	}

	public Gravetat (String DESCRIPCIO) {
		this.DESCRIPCIO=DESCRIPCIO;
	}
	
}