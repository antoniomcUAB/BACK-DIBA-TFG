package es.in2.dsdibaapi.model;

import java.io.Serializable;

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
@Table (name="RISC")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Risc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private long id;
	private Integer value;
	private String descripcio;
	
	

}