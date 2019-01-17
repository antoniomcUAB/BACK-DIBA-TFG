package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="VERSIO_MODEL")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class VersioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private long ID;
	
	private String versio;
	private Date data;
	
	

}
