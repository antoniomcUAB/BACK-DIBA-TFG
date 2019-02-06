package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table (name="ENTORN")
@JsonPropertyOrder({ "id", "descripcio", "preguntes" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Entorn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long id;
	private String descripcio;
	
	
	@ManyToOne
    @JoinColumn(name="ambit",foreignKey= @ForeignKey(name = "ENTORN_AMBIT_FK"))
	@JsonIgnore
    private Ambit ambit;
	
	@OneToMany (mappedBy = "entorn")
	@JsonIgnoreProperties(value = { "vulnerabilitat", "risc", "altRisc"})
	@OrderBy("id ASC")
	@JsonProperty("preguntes")
    private List<SituacioSocial> situacioSocial;
	
	
	@Transient
	@JsonIgnoreProperties(value = { "entorn"})
	private Iterable<Pregunta> pregunta;
	
	
	
}