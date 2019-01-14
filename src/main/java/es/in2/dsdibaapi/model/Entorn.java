package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Entity
@Table (name="ENTORN")
@JsonPropertyOrder({ "ID", "DESCRIPCIO", "items" })
public @Data class Entorn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String DESCRIPCIO;
	
	@ManyToOne
    @JoinColumn(name="ambit",foreignKey= @ForeignKey(name = "ENTORN_AMBIT_FK"))
	@JsonIgnore
    private Ambit ambit;
	
	@OneToMany (mappedBy = "entorn")
	@JsonProperty("Items")
    private List<SituacioSocial> situacioSocial;
	
	@OneToMany (mappedBy = "entorn")
	@JsonProperty("Factors_context")
    private List<Factor> factorGravetat;
	
	public Entorn () {
		
	}

	public Entorn (String DESCRIPCIO, Ambit AMBIT) {
		this.DESCRIPCIO=DESCRIPCIO;
		this.ambit = AMBIT;
	}
}