package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="CONTEXTUALITZACIO")
public @Data class Contextualitzacio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long ID;
	private String AMBIT;
	private String CODI;
	private String DESCRIPCIO;

}