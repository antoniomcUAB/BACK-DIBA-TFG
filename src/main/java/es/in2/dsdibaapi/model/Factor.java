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
@Table (name="FACTOR")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Factor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String descripcio;
	private Double fc1m;
	private Double fctots;
	@Builder.Default private Boolean infants = false;
	
	@ManyToOne
	@JoinColumn(name="gravetat",foreignKey= @ForeignKey(name = "FACTOR_GRAVETAT_FK"))
    private Gravetat gravetat;
		
	@ManyToOne
    @JoinColumn(name="ambit",foreignKey= @ForeignKey(name = "FACTOR_AMBIT_FK"))
	@JsonIgnore
    private Ambit ambit;


}
