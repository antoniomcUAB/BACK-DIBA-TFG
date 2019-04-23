package es.in2.dsdibaapi.model.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement (name = "ws")
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class ValidacioUsuari  implements Serializable {
	
	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "nom")
	private String nom;
	
	@XmlElement(name = "versio_servweb")
	private String versio_servweb;
	
	@XmlElement(name = "resposta")
	private ValidacioUsuariResposta resposta;
	
}
