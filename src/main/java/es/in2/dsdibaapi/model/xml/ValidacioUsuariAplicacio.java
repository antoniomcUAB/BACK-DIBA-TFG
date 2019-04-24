package es.in2.dsdibaapi.model.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
public @Data class ValidacioUsuariAplicacio  implements Serializable {
	
	@XmlElement(name = "codi")
	private String codi;
	
	@XmlElement(name = "descr")
	private String descr;
	
	@XmlElement(name = "perfil")
	private ValidacioUsuariObject perfil;
}
