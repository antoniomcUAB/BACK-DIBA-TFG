package es.in2.dsdibaapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
public @Data class JWTProperties {
	
	@Value( "${jwt.header.issuer}" )
	private String ISSUER_INFO = "DSDIBA-API";
	
	@Value( "${jwt.header.token.expiration}" )
	private Long TOKEN_EXPIRATION_TIME;
	
	@Value( "${jwt.key}" )
	private String JWT_KEY;
	
	@Value( "${jwt.header.token.bearer.prefix}" )
	private String TOKEN_BEARER_PREFIX;
	
	@Value( "${jwt.header.authorization.key}" )
	private String HEADER_AUTHORIZATION_KEY;
	
	@Value( "${vus.url}" )
	private String VUS_URL;
	
	@Value( "${vus.clau.key}" )
	private String VUS_CLAU_KEY;
	
	@Value( "${vus.clau.value}" )
	private String VUS_CLAU_VALUE;
	
	@Value( "${vus.usuari.key}" )
	private String VUS_USUARI_KEY;
	
	@Value( "${vus.usuari.value}" )
	private String VUS_USUARI_VALUE;
	
	@Value( "${vus.ws.clau.key}" )
	private String VUS_WS_CLAU_KEY;
	
	@Value( "${vus.ws.usuari.key}" )
	private String VUS_WS_USUARI_KEY;
	
}
