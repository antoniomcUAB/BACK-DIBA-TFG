package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table (name="EXPEDIENT")
public @Data class Expedient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long ID;
	private String EXPEDIENT;
	private String PROFESSIONAL;
	private String NOM;
	private String DATA;
	private String OBSERVACIONS;

	@OneToOne
    @JoinColumn(name = "id")
	@JsonIgnore
    private Expedient expedient;
}