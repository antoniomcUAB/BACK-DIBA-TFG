package es.in2.dsdibaapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.in2.dsdibaapi.model.Professional;
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
			Professional credenciales = new ObjectMapper().readValue(request.getInputStream(), Professional.class);
			//User credenciales = new ObjectMapper().readValue(request.getInputStream(), User.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(jwtProperties.getISSUER_INFO())
				//.setSubject(((Professional)auth.getPrincipal()).getUsername())
				.setSubject(((User)auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getTOKEN_EXPIRATION_TIME()))
				.signWith(SignatureAlgorithm.HS512, jwtProperties.getJWT_KEY()).compact();
		response.addHeader(jwtProperties.getHEADER_AUTHORIZATION_KEY(), jwtProperties.getTOKEN_BEARER_PREFIX() + " " + token);
	}
}
