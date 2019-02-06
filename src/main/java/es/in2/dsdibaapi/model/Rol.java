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
@Table (name="ROL")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private long id;
	
	private String descripcio;
	
	

}