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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="AVALUACIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Avaluacio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private long ID;
	
	@ManyToOne
    @JoinColumn(name="ambit",foreignKey= @ForeignKey(name = "AVALUACIO_ENTORN_FK"))	
    private Ambit ambit;
	
	
	@ManyToOne
	@JoinColumn(name="valoracio",foreignKey= @ForeignKey(name = "AVALUACIO_VALORACIO_FK"))
	@JsonIgnore
    private Valoracio valoracio;
	
	@ManyToOne
	@JoinColumn(name="risc",foreignKey= @ForeignKey(name = "AVALUACIO_RISC_FK"))
    private Risc risc;
	
	@ManyToOne
	@JoinColumn(name="riscProfessional",foreignKey= @ForeignKey(name = "AVALUACIO_RISC_PROF_FK"))
    private Risc riscProfessional;
	
	

}