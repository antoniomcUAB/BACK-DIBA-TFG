package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;import javax.persistence.GenerationType; import javax.persistence.SequenceGenerator;
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
@Table (name="DIBA_PRF_PROFESSIONAL")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Professional implements Serializable , UserDetails  {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue  //(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")	@SequenceGenerator(name="HIBERNATE_SEQUENCE", sequenceName = "HIBERNATE_SEQUENCE") 
	@Column (name="DIBA_PRF_ID")
	private long id;
	@Column (name="DIBA_PRF_NOM")
	private String nom;
	@Column (name="DIBA_PRF_COGNOM1")
	private String cognom1;
	@Column (name="DIBA_PRF_COGNOM2")
	private String cognom2;
	@Column (name="DIBA_PRF_NOM_COMPLET")
	private String nomComplet;
	
	@JsonIgnore 
	@Column (name="DIBA_PRF_PASSWORD")
	private String password;
	@Column (name="DIBA_PRF_USERNAME")
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
       name = "DIBA_PRR_PROFESSIONAL_ROL",  
       joinColumns = { @JoinColumn(name = "DIBA_PRR_PROFESSIONAL",foreignKey = @ForeignKey(name = "DIBA_PRR_FK_PRF")) },         
       inverseJoinColumns = { @JoinColumn(name = "DIBA_PRR_ROL",foreignKey = @ForeignKey(name = "DIBA_PRR_FK_ROL")) }
   )	
   private Set<Rol> rol = new HashSet<>();
	
	@ManyToOne
    @JoinColumn(name="DIBA_PRF_MUNICIPI",foreignKey= @ForeignKey(name = "DIBA_PRF_PROFESSIONAL_FK_MUN"))
	private Municipi municipi;
	
	@OneToMany(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name="DIBA_EXP_PROFESSIONAL",referencedColumnName="DIBA_PRF_ID",foreignKey= @ForeignKey(name = "DIBA_EXP_EXPEDIENT_FK_PRF"))
	@JsonIgnore
    private List<Expedient> expedient;

	
	
}