package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="ECONOMIA")
public @Data class Economia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long ID_EXPEDIENT;
	private String VALOR;
	private String FACTOR;

}