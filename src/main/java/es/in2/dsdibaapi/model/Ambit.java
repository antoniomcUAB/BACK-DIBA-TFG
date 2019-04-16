package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_AMB_AMBIT")
@JsonPropertyOrder({ "id", "descripcio", "entorns" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Ambit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue //(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE") 
	@Column(name = "DIBA_AMB_ID")
	private long id;
	@Column(name = "DIBA_AMB_DESCRIPCIO")
	private String descripcio;
	@Column(name = "DIBA_AMB_VULNERABILITAT")
	private Double vulnerabilitat;
	@Column(name = "DIBA_AMB_RISC")
	private Double risc;
	@Column(name = "DIBA_AMB_VAL_VULNERABILITAT")
	private Double valVulnerabilitat;
	@Column(name = "DIBA_AMB_VAL_RISC")
	private Double valRisc;
	@Column(name = "DIBA_AMB_VAL_ALTRISC")
	private Double valAltrisc;
	
	@OneToMany(mappedBy = "ambit")
	@OrderBy("id ASC")
	@JsonProperty("entorns")
	@LazyCollection(LazyCollectionOption.FALSE)
    private List<Entorn> entorn;
	
	@OneToMany (mappedBy = "ambit")
	@JsonIgnoreProperties(value = { "fc1m"})
	@JsonProperty("factors_context")
	@OrderBy("id ASC")
    private List<Factor> factorGravetat;
	
	@ManyToOne
    @JoinColumn(name="DIBA_AMB_VERSIO_MODEL",foreignKey= @ForeignKey(name = "DIBA_AMB_AMBIT_FK_VSM"), updatable=false)
	@JsonIgnore
    private VersioModel versioModel;
	
	
	
	@Transient	
	private Iterable<Contextualitzacio> contextualitzacio;
	
}