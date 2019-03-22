package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="PROFESSIONAL")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Professional implements Serializable , UserDetails  {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;
	private String nom;
	private String cognom1;
	private String cognom2;
	private String nomComplet;
	
	@JsonIgnore 
	private String password;
	
	private String username;
	
	
	@Override
	@JsonIgnore 
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.rol.stream().map(x-> {return new SimpleGrantedAuthority (x.getDescripcio());}).collect(Collectors.toList());
    }
	
	 @Override
	 @JsonIgnore 
	    public boolean isAccountNonExpired() {
	        return true;
	    }
	    @Override
	    @JsonIgnore 
	    public boolean isAccountNonLocked() {
	        return true;
	    }
	    @Override
	    @JsonIgnore 
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	    @Override
	    @JsonIgnore 
	    public boolean isEnabled() {
	        return true;
	    }
	
	
	
	
	@ManyToMany (fetch = FetchType.EAGER)
  	 @JoinTable(
       name = "professional_rol",  
       joinColumns = { @JoinColumn(name = "professional",foreignKey = @ForeignKey(name = "PROFESSIONAL_ROL_PRO_FK")) },         
       inverseJoinColumns = { @JoinColumn(name = "rol",foreignKey = @ForeignKey(name = "PROFESSIONAL_ROL_ROL_FK")) }
   )	
   private Set<Rol> rol = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="municipi",foreignKey= @ForeignKey(name = "PROFESSIONAL_MUNICIPI_FK"))	
    private Municipi municipi;
	
	@OneToMany(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name="professional",referencedColumnName="id",foreignKey= @ForeignKey(name = "EXPEDIENT_PROFESSIONAL_FKv2"))
	@JsonIgnore
    private List<Expedient> expedient;

	
	
}