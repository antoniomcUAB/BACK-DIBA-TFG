package es.in2.dsdibaapi.model.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
public @Data class ValidacioUsuariVus  implements Serializable {
	
	@XmlElement(name = "usuari")
	private String usuari;
	
	@XmlElement(name = "nom")
	private String nom;
	
	@XmlElementWrapper(name="lens")
    @XmlElement(name="ens")
    private List<ValidacioUsuariEns> ens;
}
