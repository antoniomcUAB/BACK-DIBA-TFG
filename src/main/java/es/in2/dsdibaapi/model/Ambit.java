package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="AMBIT")
@JsonPropertyOrder({ "ID", "DESCRIPCIO", "items" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Ambit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String DESCRIPCIO;
	private Double vulnerabilitat;
	private Double risc;
	private Double valVulnerabilitat;
	private Double valRisc;
	private Double valAltrisc;
	
	@OneToMany(mappedBy = "ambit")
	@JsonProperty("Items")
    private List<Entorn> entorn;
	
	@OneToMany (mappedBy = "ambit")
	@JsonProperty("Factors_context")
    private List<Factor> factorGravetat;
	
	
}