package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_FAE_FACTORECONOMIC")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class FactorEconomic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	@Column (name="DIBA_FAE_ID")
	private long id;
	@Column (name="DIBA_FAE_DESCRIPCIO")
	private String descripcio;
	

	@ManyToOne
    @JoinColumn(name="DIBA_FAE_VERSIO_MODEL",foreignKey= @ForeignKey(name = "DIBA_FAE_FACTORECONOMIC_FK_VSM"),updatable=false)
	@JsonIgnore
	private VersioModel versioModel;
	
}