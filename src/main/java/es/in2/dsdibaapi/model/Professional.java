package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="PROFESSIONAL")
public @Data class Professional implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String nom;
	private String cognom1;
	private String cognom2;
		
	
	@ManyToMany(cascade = { CascadeType.MERGE } )
  	 @JoinTable(
       name = "professional_rol", 
       joinColumns = { @JoinColumn(name = "professional",foreignKey = @ForeignKey(name = "PROFESSIONAL_ROL_PRO_FK")) },         
       inverseJoinColumns = { @JoinColumn(name = "rol",foreignKey = @ForeignKey(name = "PROFESSIONAL_ROL_ROL_FK")) }
   )	
   private Set<Rol> rol = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="municipi",foreignKey= @ForeignKey(name = "PROFESSIONAL_MUNICIPI_FK"))	
    private Municipi municipi;

	
	public Professional () {
		
	}

	public Professional (String nom,String cognom1,String cognom2,Municipi municipi,Set<Rol> rol) {
		this.nom=nom;
		this.cognom1=cognom1;
		this.cognom2=cognom2;
		this.municipi=municipi;
		this.rol=rol;
	}
}