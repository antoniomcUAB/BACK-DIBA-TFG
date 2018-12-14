package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="QUESTIO")
public @Data class ModalitatDiagnostic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String CODI;
	private String DESCRIPCIO;
	
	@OneToOne
    @JoinColumn(name = "id")
    private ModalitatDiagnostic modalitatDiagnostic;

}