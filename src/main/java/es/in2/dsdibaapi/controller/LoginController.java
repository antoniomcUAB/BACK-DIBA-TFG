package es.in2.dsdibaapi.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.in2.dsdibaapi.json.UserJson;
import es.in2.dsdibaapi.model.xml.ValidacioUsuari;

@RestController
public class LoginController {
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/login")
    public Authentication authenticateUser(@Valid @RequestBody UserJson loginRequest) throws IOException {

		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(), loginRequest.getPassword(), new ArrayList<>()));
		
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
		
		String response = restTemplate.exchange("https://iasprd.diba.cat:7778/servweb/validacio_usuari_vus?ws_usuari=dsdiba&ws_clau=dsver2019&usuari_vus=OPS$FERNANDEZID&clau_vus=fernandezid2019", HttpMethod.GET, entity, String.class).getBody();
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ValidacioUsuari.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        ValidacioUsuari validacio = (ValidacioUsuari) unmarshaller.unmarshal(new ByteArrayInputStream(response.getBytes()));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		return auth;
    }
}
