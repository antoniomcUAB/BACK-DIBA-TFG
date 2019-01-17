package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="FACTOR")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Factor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private long ID;
	private String descripcio;
	private Double fc1m;
	private Double fctots;
	
	@ManyToOne
	@JoinColumn(name="gravetat",foreignKey= @ForeignKey(name = "FACTOR_GRAVETAT_FK"))
    private Gravetat gravetat;
		
	@ManyToOne
    @JoinColumn(name="entorn",foreignKey= @ForeignKey(name = "FACTOR_ENTORN_FK"))
	@JsonIgnore
    private Entorn entorn;


	public Factor (String descripcio, Gravetat gravetat, Entorn entorn) {
		this.descripcio=descripcio;
		this.gravetat=gravetat;
		this.entorn=entorn;
	}
}
