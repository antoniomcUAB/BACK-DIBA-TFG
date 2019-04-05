package es.in2.dsdibaapi.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import es.in2.dsdibaapi.model.id.EconomiaId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Table (name="DIBA_ECO_ECONOMIA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Economia implements Serializable {

	private static final long serialVersionUID = 1L;

	 @EmbeddedId EconomiaId id;
	
	
	
}