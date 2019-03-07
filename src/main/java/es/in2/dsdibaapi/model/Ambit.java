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
@Table (name="AMBIT")
@JsonPropertyOrder({ "id", "descripcio", "entorns" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Ambit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;
	private String descripcio;
	private Double vulnerabilitat;
	private Double risc;
	private Double valVulnerabilitat;
	private Double valRisc;
	private Double valAltrisc;
	
	@OneToMany(mappedBy = "ambit")
	@OrderBy("id ASC")
	@JsonProperty("entorns")
	@LazyCollection(LazyCollectionOption.FALSE)
    private List<Entorn> entorn;
	
	@OneToMany (mappedBy = "ambit")
	@JsonIgnoreProperties(value = { "fc1m"})
	@JsonProperty("factors_context")	
    private List<Factor> factorGravetat;
	
	@ManyToOne
    @JoinColumn(name="versioModel",foreignKey= @ForeignKey(name = "AMBIT_VERSIO_MODEL_FK"))
	@JsonIgnore
    private VersioModel versioModel;
	
	
	
	@Transient	
	private Iterable<Contextualitzacio> contextualitzacio;
	
}