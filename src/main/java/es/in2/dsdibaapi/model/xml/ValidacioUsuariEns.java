package es.in2.dsdibaapi.model.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
public @Data class ValidacioUsuariEns  implements Serializable {
	
	@XmlElement(name = "codi")
	private String codi;
	
	@XmlElement(name = "descr")
	private String descr;
	
	@XmlElementWrapper(name="laplicacions")
    @XmlElement(name="aplicacio")
    private List<ValidacioUsuariAplicacio> aplicacio;
}
