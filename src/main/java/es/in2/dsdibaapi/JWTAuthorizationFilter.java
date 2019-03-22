package es.in2.dsdibaapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter  extends BasicAuthenticationFilter {
	
	JWTProperties jwtProperties;
	
	
	public JWTAuthorizationFilter(AuthenticationManager authManager, JWTProperties jwtProperties) {
		super(authManager);
		this.jwtProperties = jwtProperties;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		try {
		
			String header = req.getHeader(jwtProperties.getHEADER_AUTHORIZATION_KEY());
			if (header == null || !header.startsWith(jwtProperties.getTOKEN_BEARER_PREFIX())) {
				ObjectMapper objectMapper = new ObjectMapper();
				
				res.setStatus(HttpStatus.UNAUTHORIZED.value());
		        Map<String, Object> data = new HashMap<>();
		        data.put(
		          "timestamp", 
		          Calendar.getInstance().getTime());
		        data.put(
		          "message", 
		          "Token incorrecte");
		        
		        res.getOutputStream()
		          .println(objectMapper.writeValueAsString(data));
			}
			else {
				UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				chain.doFilter(req, res);
			}
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException s) {
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			res.setStatus(HttpStatus.UNAUTHORIZED.value());
	        Map<String, Object> data = new HashMap<>();
	        data.put(
	          "timestamp", 
	          Calendar.getInstance().getTime());
	        data.put(
	          "message", 
	          "Token incorrecte");
	        data.put(
		  	          "exception", 
		  	          s.getMessage());
	        res.getOutputStream()
	          .println(objectMapper.writeValueAsString(data));
			
		} catch (ExpiredJwtException e) {
			ObjectMapper objectMapper = new ObjectMapper();
			
			res.setStatus(HttpStatus.UNAUTHORIZED.value());
	        Map<String, Object> data = new HashMap<>();
	        data.put(
	          "timestamp", 
	          Calendar.getInstance().getTime());
	        data.put(
	          "message", 
	          "Token expirat");
	        data.put(
	  	          "exception", 
	  	          e.getMessage());
	 
	        res.getOutputStream()
	          .println(objectMapper.writeValueAsString(data));
		} 
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(jwtProperties.getHEADER_AUTHORIZATION_KEY());
		if (token != null) {
			
			String user = Jwts.parser()
						.setSigningKey(jwtProperties.getJWT_KEY())
						.parseClaimsJws(token.replace(jwtProperties.getTOKEN_BEARER_PREFIX(), ""))
						.getBody()
						.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}
