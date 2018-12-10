package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="EX_REFERENCIA")
public @Data class ExReferencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long ID_EXPEDIENT;
	
	
	
}