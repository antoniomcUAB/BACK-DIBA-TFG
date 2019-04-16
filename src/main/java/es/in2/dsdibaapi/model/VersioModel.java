package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	public static final String PEC_SEPARADOR = "_";

	@Id 
	@GeneratedValue  //(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE")
	@Column(name = "DIBA_VSM_ID")
	private long id;
	@Column(name = "DIBA_VSM_VERSIO")
	private String versio;
	@Column(name = "DIBA_VSM_DATA")
	private Date data;
	@Column(name = "DIBA_VSM_PREGUNTA_ECONOMICA")
	@JsonIgnore
	private String preguntaEconomica;
	
	@Transient
	private List<String> llistaPE;
	
	public List<String> getLlistaPE () {
		if (preguntaEconomica != null) {
			return Arrays.asList(preguntaEconomica.split(PEC_SEPARADOR));
		}
		
		return null;
	}

}
