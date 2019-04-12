package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_FCT_FACTOR")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Factor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue  (strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "DSDIBA.HIBERNATE_SEQUENCE") 
	@Column (name="DIBA_FCT_ID")
	private long id;
	@Column (name="DIBA_FCT_DESCRIPCIO")
	private String descripcio;
	@Column (name="DIBA_FCT_FC1M")
	private Double fc1m;
	@Column (name="DIBA_FCT_FCTOTS")
	private Double fctots;
	@Column (name="DIBA_FCT_INFANTS")
	@Builder.Default private Boolean infants = false;
	
	@ManyToOne
	@JoinColumn(name="DIBA_FCT_GRAVETAT",foreignKey= @ForeignKey(name = "DIBA_FCT_FACTOR_FK_GRA"))
	@JsonIgnoreProperties(value = { "value"} )
	@OrderBy("id ASC")
	private Gravetat gravetat;
		
	@ManyToOne
    @JoinColumn(name="DIBA_FCT_AMBIT",foreignKey= @ForeignKey(name = "DIBA_FCT_FACTOR_FK_AMB"))	
	@JsonIgnoreProperties(value = { "entorns", "factors_context", "vulnerabilitat", "risc", "valVulnerabilitat", "valRisc", "valAltrisc"} )
	private Ambit ambit;
	


}
