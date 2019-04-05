package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
@Table (name="DIBA_AMD_AMBITDIAGNOSTIC")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class AmbitDiagnostic implements Serializable {
	
	
	@Id @GeneratedValue
	@Column(name = "DIBA_AMD_ID")
	private long id;
	@Column(name = "DIBA_AMD_OBSERVACIONS")
	private String observacions;
		
	@ManyToOne
    @JoinColumn(name="DIBA_AMD_AMBIT",foreignKey= @ForeignKey(name = "DIBA_AMD_FK_AMB"), updatable=false)	
	@JsonIgnoreProperties(value = { "vulnerabilitat", "risc", "valVulnerabilitat", "valRisc", "valAltrisc", "entorns", "factors_context"})
	private Ambit ambit;
	
	
	@ManyToOne
    @JoinColumn(name="DIBA_AMD_DIAGNOSTIC",foreignKey= @ForeignKey(name = "DIBA_AMD_FK_DGC"), updatable=false)
	@JsonIgnore
	private Diagnostic diagnostic;
	
	@Transient
	private List<EntornJson> entorn;
	
	@Transient
	private Iterable<Contextualitzacio> contextualitzacio;
	
	
}
