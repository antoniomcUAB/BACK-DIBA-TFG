package es.in2.dsdibaapi.model.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

//@XmlRootElement (name = "ws")
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class ValidacioUsuariObject  implements Serializable {
	
	@XmlElement(name = "codi")
	private String codi;
	
	@XmlElement(name = "descr")
	private String descr;
	
	
}
