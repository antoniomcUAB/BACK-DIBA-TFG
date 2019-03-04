package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="AVALUACIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Avaluacio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private long id;
	private String justificacio;
	
	@ManyToOne
    @JoinColumn(name="ambit",foreignKey= @ForeignKey(name = "AVALUACIO_ENTORN_FK"))
	@JsonIgnoreProperties(value = { "diagnostic", "entorn", "contextualitzacio"})
    private AmbitDiagnostic ambit;
	
	
	@ManyToOne 
	@JoinColumn(name="valoracio",foreignKey= @ForeignKey(name = "AVALUACIO_VALORACIO_FK"), updatable = false)
	@JsonIgnore
    private Valoracio valoracio;
	
	@ManyToOne
	@JoinColumn(name="risc",foreignKey= @ForeignKey(name = "AVALUACIO_RISC_FK"))
	@JsonIgnoreProperties(value = { "value"})
    private Risc risc;
	
	@ManyToOne
	@JoinColumn(name="riscProfessional",foreignKey= @ForeignKey(name = "AVALUACIO_RISC_PROF_FK"))
    private Risc riscProfessional;
	
	

}