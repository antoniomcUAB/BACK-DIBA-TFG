package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="PERSONA")
public @Data class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String NOM;
	private String COGNOM1;
	private String COGNOM2;

	/*@ManyToMany(cascade = { CascadeType.ALL } )
    @JoinTable(
        name = "ex_referencia", 
        joinColumns = { @JoinColumn(name = "expedient",foreignKey = @ForeignKey(name = "EX_REFERENCIA_EXPEDIENT_FK"),referencedColumnName = "id") },         
        inverseJoinColumns = { @JoinColumn(name = "persona",foreignKey = @ForeignKey(name = "EX_REFERENCIA_PERSONA_FK"),referencedColumnName = "id") }
    )
    Set<Expedient> expedient; // = new HashSet<Expedient>();*/
	
	
	
	public Persona () {
		
	}

	public Persona (String NOM,String COGNOM1,String COGNOM2) {
		this.NOM=NOM;
		this.COGNOM1=COGNOM1;
		this.COGNOM2=COGNOM2;
	}
}