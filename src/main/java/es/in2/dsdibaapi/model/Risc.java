package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_RIS_RISC")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Risc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue  //(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE")
	@Column(name = "DIBA_RIS_ID")
	private long id;
	@Column(name = "DIBA_RIS_VALUE")
	private Integer value;
	@Column(name = "DIBA_RIS_DESCRIPCIO")
	private String descripcio;
	
	

}