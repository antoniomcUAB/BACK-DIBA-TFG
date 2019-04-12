package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_EXP_EXPEDIENT")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Expedient implements Serializable {
	
	
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "DSDIBA.HIBERNATE_SEQUENCE")
	@Column (name="DIBA_EXP_ID")
	private long id;
	@Column (name="DIBA_EXP_CODI")
	private String codi;
	@Column (name="DIBA_EXP_DATA_CREACIO")
	private Date dataCreacio;
	@Column (name="DIBA_EXP_DATA_VALIDACIO")
	private Date dataValidacio;
	@Column (name="DIBA_EXP_TOTAL_FAMILIA")
	private Long totalFamilia;
	@Column (name="DIBA_EXP_OBSERVACIONS")
	private String observacions;
	
	@Transient
	private Long diagnosticsValidats;	
	
	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name="DIBA_PER_EXPEDIENT",referencedColumnName="DIBA_EXP_ID")
	@JsonProperty("persona")
	private Set<Persona> persona;
	
	@ManyToOne
    @JoinColumn(name="DIBA_EXP_ESTAT",foreignKey= @ForeignKey(name = "DIBA_EXP_EXPEDIENT_FK_EST"))
	private Estat estat;
	

	@ManyToOne
    @JoinColumn(name="DIBA_EXP_PROFESSIONAL",foreignKey= @ForeignKey(name = "DIBA_EXP_EXPEDIENT_FK_PRF"))		
	private Professional professional;
		
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="DIBA_DGC_EXPEDIENT")
	@OrderBy(value = "data DESC")
    private List<Diagnostic> diagnostic;
	
}
