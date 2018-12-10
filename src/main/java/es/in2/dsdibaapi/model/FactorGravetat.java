package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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

	@Id
	private long ID;
	
	@OneToOne(mappedBy = "gravetat")
    private Gravetat gravetat;
	
	@OneToOne(mappedBy = "factor")
    private Factor factor;
	
	@ManyToOne
    @JoinColumn(name="ambit",foreignKey= @ForeignKey(name = "FACTOR_GRAVETAT_AMBIT_FK"))
	@JsonIgnore
    private Ambit ambit;

}