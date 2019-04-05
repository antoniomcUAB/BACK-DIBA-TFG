package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_ENT_ENTORN")
@JsonPropertyOrder({ "id", "descripcio", "preguntes" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Entorn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	@Column (name="DIBA_ENT_ID")
	private long id;
	@Column (name="DIBA_ENT_DESCRIPCIO")
	private String descripcio;
	
	
	@ManyToOne
    @JoinColumn(name="DIBA_ENT_AMBIT",foreignKey= @ForeignKey(name = "DIBA_ENT_ENTORN_FK_AMB"))
	@JsonIgnore	
	private Ambit ambit;
	
	@OneToMany (mappedBy = "entorn")
	@JsonIgnoreProperties(value = { "vulnerabilitat", "risc", "altRisc"})
	@OrderBy("id ASC")
	@JsonProperty("preguntes")
    private List<SituacioSocial> situacioSocial;
	
	
	
	
	
}