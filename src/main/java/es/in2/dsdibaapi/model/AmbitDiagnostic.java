package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.in2.dsdibaapi.json.EntornJson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="AMBIT_DIAGNOSTIC")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class AmbitDiagnostic implements Serializable {
	
	
	@Id @GeneratedValue 
	private long id;
	private String observacions;
		
	@ManyToOne
    @JoinColumn(name="ambit",foreignKey= @ForeignKey(name = "AMBIT_DIAGNOSTIC_AMBIT_FK"), updatable=false)	
	@JsonIgnoreProperties(value = { "vulnerabilitat", "risc", "valVulnerabilitat", "valRisc", "valAltrisc", "entorns", "factors_context"})
    private Ambit ambit;
	
	
	@ManyToOne
    @JoinColumn(name="diagnostic",foreignKey= @ForeignKey(name = "AMBIT_DIAGNOSTIC_DIAGNOSTIC_FK"), updatable=false)
	@JsonIgnore
    private Diagnostic diagnostic;
	
	@Transient
	private List<EntornJson> entorn;
	
	@Transient
	private Iterable<Contextualitzacio> contextualitzacio;
	
	/*
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ambit")
    private List<Pregunta> pregunta;
	*/
	
}
