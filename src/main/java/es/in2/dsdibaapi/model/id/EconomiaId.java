package es.in2.dsdibaapi.model.id;

import java.io.Serializable;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.Pregunta;

public class EconomiaId implements Serializable {
	
	@ManyToOne
	@JoinColumn(name="DIBA_ECO_PREGUNTA",foreignKey= @ForeignKey(name = "DIBA_ECO_ECONOMIA_FK_PRE"))
	@JsonIgnore
	private Pregunta pregunta;
	
	@ManyToOne
    @JoinColumn(name="DIBA_ECO_FACTOR",foreignKey= @ForeignKey(name = "DIBA_ECO_ECONOMIA_FK_FAE"))
	private FactorEconomic factor;
}
