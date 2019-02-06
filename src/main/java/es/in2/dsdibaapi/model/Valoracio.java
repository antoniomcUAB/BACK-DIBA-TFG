package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="VALORACIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Valoracio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private long id;
	
	private Double total;
	private long factors;
	private Date data;
	/*
	@OneToOne
	@JoinColumn(name="expedient",foreignKey= @ForeignKey(name = "VALORACIO_EXPEDIENT_FK"))
	@JsonIgnore
    private Expedient expedient;*/
	
	/*@OneToMany (cascade=CascadeType.ALL,mappedBy = "valoracio", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)*/
	@OneToMany (cascade= {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name="valoracio",referencedColumnName="id")
	@JsonIgnoreProperties(value = { "value"})
	@JsonProperty("evaluacions")
    private List<Avaluacio> avaluacio;
	

}