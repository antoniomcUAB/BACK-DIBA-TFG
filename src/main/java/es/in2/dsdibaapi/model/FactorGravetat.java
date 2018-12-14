package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table (name="FACTOR_GRAVETAT")
public @Data class FactorGravetat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	
	@OneToOne
	@JoinColumn(name="gravetat",foreignKey= @ForeignKey(name = "FACTOR_GRAVETAT_GRAVETAT_FK"))
    private Gravetat gravetat;
	
	@OneToOne
	@JoinColumn(name="factor",foreignKey= @ForeignKey(name = "FACTOR_GRAVETAT_FACTOR_FK"))
    private Factor factor;
	
	@ManyToOne
    @JoinColumn(name="entorn",foreignKey= @ForeignKey(name = "FACTOR_GRAVETAT_ENTORN_FK"))
	@JsonIgnore
    private Entorn entorn;

	public FactorGravetat () {
		
	}

	public FactorGravetat ( Gravetat gravetat, Factor factor, Entorn entorn) {
		this.gravetat=gravetat;
		this.factor=factor;
		this.entorn=entorn;
	}
}