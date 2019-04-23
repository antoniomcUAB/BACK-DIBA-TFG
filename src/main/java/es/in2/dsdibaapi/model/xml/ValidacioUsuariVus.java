package es.in2.dsdibaapi.model.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

//@XmlRootElement (name = "ws")
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class ValidacioUsuariVus  implements Serializable {
	
	@XmlElement(name = "usuari")
	private String usuari;
	
	@XmlElement(name = "nom")
	private String nom;
	
	
}
