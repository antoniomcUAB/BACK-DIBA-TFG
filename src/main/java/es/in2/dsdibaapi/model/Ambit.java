package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@Table (name="AMBIT")
@JsonPropertyOrder({ "ID", "DESCRIPCIO", "items" })
public @Data class Ambit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String DESCRIPCIO;
	
	
	@OneToMany(mappedBy = "ambit")
	@JsonProperty("Items")
    private List<Entorn> entorn;
	
	public Ambit () {
		
	}

	public Ambit (String DESCRIPCIO) {
		this.DESCRIPCIO=DESCRIPCIO;
	}
	
}