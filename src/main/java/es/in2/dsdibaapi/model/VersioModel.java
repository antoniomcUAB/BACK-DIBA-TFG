package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_VSM_VERSIOMODEL")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class VersioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	@Column(name = "DIBA_VSM_ID")
	private long id;
	@Column(name = "DIBA_VSM_VERSIO")
	private String versio;
	@Column(name = "DIBA_VSM_DATA")
	private Date data;
	@Column(name = "DIBA_VSM_PREGUNTA_ECONOMICA")
	private Long preguntaEconomica;
	
	

}
