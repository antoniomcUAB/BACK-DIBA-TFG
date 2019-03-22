package es.in2.dsdibaapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.in2.dsdibaapi.json.UserJson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {
	
	JWTProperties jwtProperties;
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTProperties jwtProperties) {
		this.authenticationManager = authenticationManager;
		this.jwtProperties = jwtProperties;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserJson credenciales = new ObjectMapper().readValue(request.getInputStream(), UserJson.class);
			
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
			
			return auth;
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
