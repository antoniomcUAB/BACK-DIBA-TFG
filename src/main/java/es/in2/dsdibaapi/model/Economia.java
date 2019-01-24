package es.in2.dsdibaapi.model;

import java.io.Serializable;

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
@Table (name="ECONOMIA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Economia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;
	
	@ManyToOne
	@JoinColumn(name="diagnostic",foreignKey= @ForeignKey(name = "ECONOMIA_DIAGNOSTIC_FK"))
	@JsonIgnore
    private Diagnostic diagnostic;
	
	@ManyToOne
    @JoinColumn(name="factor",foreignKey= @ForeignKey(name = "ECONOMIA_FACTOR_ECONOMIC_FK"))	
	private FactorEconomic factor;
	
	
	
}