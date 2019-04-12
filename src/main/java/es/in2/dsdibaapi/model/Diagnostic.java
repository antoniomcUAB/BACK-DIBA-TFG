package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_DGC_DIAGNOSTIC")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Diagnostic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "DSDIBA.HIBERNATE_SEQUENCE")
	@Column (name="DIBA_DGC_ID")
	private long id;
	@Column (name="DIBA_DGC_DATA")
	private Date data;
	@Column (name="DIBA_DGC_OBSERVACIONS")
	private String observacions;
	
		
	@ManyToOne
    @JoinColumn(name="DIBA_DGC_EXPEDIENT", foreignKey= @ForeignKey(name = "DIBA_DGC_DIAGNOSTIC_FK_EXP"))	
	@JsonIgnore
	private Expedient expedient;
	
	@ManyToOne
    @JoinColumn(name="DIBA_DGC_ESTAT",foreignKey= @ForeignKey(name = "DIBA_DGC_DIAGNOSTIC_FK_EST"))
	private Estat estat;
	
	@ManyToOne
    @JoinColumn(name="DIBA_DGC_VERSIO_MODEL",foreignKey= @ForeignKey(name = "DIBA_DGC_DIAGNOSTIC_FK_VSM"))
	private VersioModel versioModel;
	
	@ManyToOne
    @JoinColumn(name="DIBA_DGC_PROFESSIONAL",foreignKey= @ForeignKey(name = "DIBA_DGC_DIAGNOSTIC_FK_PRF"))	
	private Professional professional;
	
		
	@OneToOne (cascade= {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
    @JoinColumn(name="DIBA_DGC_VALORACIO",foreignKey= @ForeignKey(name = "DIBA_DGC_DIAGNOSTIC_FK_VAL"))	
	private Valoracio valoracio;	
	
	@OneToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
	@JoinColumn(name="DIBA_AMD_DIAGNOSTIC")
    private List<AmbitDiagnostic> ambit;
	
}