package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table (name="FACTOR_ECONOMIC")
public @Data class FactorEconomic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String DESCRIPCIO;
	

	@ManyToOne
    @JoinColumn(name="versioModel",foreignKey= @ForeignKey(name = "FACTOR_ECONOMIC_VERSIO_FK"))
	@JsonIgnore
    private VersioModel versioModel;
	
	public FactorEconomic () {
		
	}

	public FactorEconomic (VersioModel versioModel,String DESCRIPCIO) {
		this.DESCRIPCIO=DESCRIPCIO;
		this.versioModel=versioModel;
	}
}