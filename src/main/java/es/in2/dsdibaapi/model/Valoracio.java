package es.in2.dsdibaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table (name="DIBA_VAL_VALORACIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Valoracio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	@Column(name = "DIBA_VAL_ID")
	private long id;
	@Column(name = "DIBA_VAL_TOTAL")
	private Double total;
	@Column(name = "DIBA_VAL_FACTORS")
	private long factors;
	@Column(name = "DIBA_VAL_DATA")
	private Date data;
	@Column(name = "DIBA_VAL_CONFIRMAT")
	private Boolean confirmat;
	
	@OneToMany (cascade= {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name="DIBA_AVL_VALORACIO",referencedColumnName="DIBA_VAL_ID")
	@JsonIgnoreProperties(value = { "value"})
	@JsonProperty("evaluacions")
    private List<Avaluacio> avaluacio;
	

}