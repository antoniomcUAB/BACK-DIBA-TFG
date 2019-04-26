package es.in2.dsdibaapi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.in2.dsdibaapi.json.UserJson;
import es.in2.dsdibaapi.model.Municipi;
import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.model.Rol;
import es.in2.dsdibaapi.model.xml.ValidacioUsuari;
import es.in2.dsdibaapi.model.xml.ValidacioUsuariEns;
import es.in2.dsdibaapi.service.MunicipiService;
import es.in2.dsdibaapi.service.ProfessionalService;
import es.in2.dsdibaapi.service.RolService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
	
	JWTProperties jwtProperties;
	
	private AuthenticationManager authenticationManager;
	
	private ProfessionalService professionalService;
	
	private MunicipiService municipiService;
	
	private RolService rolService;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTProperties jwtProperties, 
							ProfessionalService professionalService, MunicipiService municipiService,
							RolService rolService) {
		this.authenticationManager = authenticationManager;
		this.jwtProperties = jwtProperties;
		this.professionalService = professionalService;
		this.municipiService = municipiService;
		this.rolService = rolService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			 {
		try {
			UserJson credenciales = new ObjectMapper().readValue(request.getInputStream(), UserJson.class);
			
			log.error("TEST LOGIN");
			
			RestTemplate restTemplate = new RestTemplate();
			
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity <String> entity = new HttpEntity<String>(headers);	      
		      
		      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(jwtProperties.getVUS_URL())		    	
		    		  .queryParam(jwtProperties.getVUS_USUARI_KEY(), credenciales.getUsername())
		    		  .queryParam(jwtProperties.getVUS_CLAU_KEY(), credenciales.getPassword())
		    		  .queryParam(jwtProperties.getVUS_WS_CLAU_KEY(), jwtProperties.getVUS_WS_CLAU_VALUE())
		    		  .queryParam(jwtProperties.getVUS_WS_USUARI_KEY(), jwtProperties.getVUS_WS_USUARI_VALUE());
			
			  
		      String resp = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class).getBody();
			
			JAXBContext jaxbContext;
			
			ValidacioUsuari validacio = null;
			
			try {
				jaxbContext = JAXBContext.newInstance(ValidacioUsuari.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		        validacio = (ValidacioUsuari) unmarshaller.unmarshal(new ByteArrayInputStream(resp.getBytes()));
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (validacio != null && validacio.getResposta().getCodi_resposta().equals("0")) {
				
				Authentication auth = null;
				
				try {
				
					auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
							credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
				} catch (AuthenticationException e) {
					Set<Rol> rols = new HashSet<Rol> ();		
					
				//	rols.add(rolService.findById(73639l));
					
					Optional<ValidacioUsuariEns> usuari = validacio.getResposta().getUsuari_vus().getEns()
						.stream()
						.filter(ens -> ens.getAplicacio().stream()
								.filter(a -> jwtProperties.getVUS_APLICACIO().equalsIgnoreCase(a.getCodi())).findFirst().isPresent()).findFirst();
					
					if (usuari.isPresent()) {
						rols.add(rolService.findByCodi(Long.valueOf(usuari.get().getAplicacio().stream()
								.filter(a -> jwtProperties.getVUS_APLICACIO().equalsIgnoreCase(a.getCodi()))
								.findFirst().get().getPerfil().getCodi())));
					}
					
					
					
					
					
					Municipi municipi = municipiService.findById(34902l);
					
					
					professionalService.save(Professional.builder()
							.nomComplet(validacio.getNom())
							.username(credenciales.getUsername())
							.password(credenciales.getPassword())
							.rol(rols)
							.municipi(municipi)
							.build());
					
					auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
							credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
				}
				
			
				
				return auth;
			} else {
			
				ObjectMapper objectMapper = new ObjectMapper();
				
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
		        Map<String, Object> data = new HashMap<>();
		        data.put(
		          "timestamp", 
		          Calendar.getInstance().getTime());
		        data.put(
		          "message", 
		          "Usuari/password incorrecte");
		        data.put(
		  	          "exception", 
		  	        validacio.getResposta().getText_resposta());
		 
		        response.getOutputStream()
		          .println(objectMapper.writeValueAsString(data));
		        
		        return null;
			}
	        
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(jwtProperties.getISSUER_INFO())
				.setSubject(((User)auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getTOKEN_EXPIRATION_TIME()))
				.signWith(SignatureAlgorithm.HS512, jwtProperties.getJWT_KEY()).compact();
		response.addHeader(jwtProperties.getHEADER_AUTHORIZATION_KEY(), jwtProperties.getTOKEN_BEARER_PREFIX() + " " + token);
		response.getWriter().write(jwtProperties.getHEADER_AUTHORIZATION_KEY()+":"+ jwtProperties.getTOKEN_BEARER_PREFIX() + " " + token);
	}
	
	@Override
	protected void unsuccessfulAuthentication(javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response,
            AuthenticationException failed)
     throws java.io.IOException,
            javax.servlet.ServletException {
		
			ObjectMapper objectMapper = new ObjectMapper();
			
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        Map<String, Object> data = new HashMap<>();
	        data.put(
	          "timestamp", 
	          Calendar.getInstance().getTime());
	        data.put(
	          "message", 
	          "Usuari/password incorrecte");
	        data.put(
	  	          "exception", 
	  	        failed.getMessage());
	 
	        response.getOutputStream()
	          .println(objectMapper.writeValueAsString(data));
	
	}
}
