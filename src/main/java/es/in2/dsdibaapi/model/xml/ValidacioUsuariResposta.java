package es.in2.dsdibaapi.model.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
public @Data class ValidacioUsuariResposta  implements Serializable {
	
	@XmlElement(name = "codi_resposta")
	private String codi_resposta;
	
	@XmlElement(name = "codi_motiu")
	private String codi_motiu;
	
	@XmlElement(name = "codi_resposta_ampliat")
	private String codi_resposta_ampliat;
	
	@XmlElement(name = "text_resposta")
	private String text_resposta;
	
	@XmlElement(name = "id_peticio")
	private String id_peticio;
	
	@XmlElement(name = "data_peticio")
	private String data_peticio;
	
	@XmlElement(name = "temps_resposta_total")
	private String temps_resposta_total;
	
	@XmlElement(name = "temps_resposta_local")
	private String temps_resposta_local;
	
	@XmlElement(name = "temps_resposta_prov1")
	private String temps_resposta_prov1;
	
	@XmlElement(name = "temps_resposta_prov2")
	private String temps_resposta_prov2;
	
	@XmlElement(name = "usuari_vus")
	private ValidacioUsuariVus usuari_vus;
	
	
}
