package es.in2.dsdibaapi;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		/*
		
		if (response.getHeader("access-control-allow-origin") == null) {
			response.setHeader("Access-Control-Allow-Origin", "http://su0353.corpo.ad.diba.es:8030");
		} else {
			response.setHeader("Access-Control-Allow-Origin", response.getHeader("access-control-allow-origin") );
		}*/
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		 
		 if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	            response.setStatus(HttpServletResponse.SC_OK);
	        }
		 
		filterChain.doFilter(request, response);
		 /*else if ((request.getHeader("origin")!=null && 
				 (request.getHeader("origin").contentEquals("http://dsdiba.demo.in2.es") ||
				  request.getHeader("origin").contentEquals("http://dsdiba.demo.in2.es") 
				 || request.getHeader("origin").contentEquals("http://localhost:7001")
				 || request.getHeader("origin").contentEquals("http://localhost:8090")
				 || request.getHeader("origin").contentEquals("http://localhost:4200"))
				 || request.getRequestURI().contains("swagger")
				 || request.getRequestURI().contains("/webjars/")
				 || request.getRequestURI().contains("/v2/")
				 ) || request.getRequestURI().contains("dsdiba")) {
			 
			 if (response.getHeader("access-control-allow-origin") == null) {
					response.setHeader("Access-Control-Allow-Origin", "http://dsdiba.demo.in2.es");
				}
			 
			 filterChain.doFilter(request, response);
		 }
		 else { 
	         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        }*/
	}

}
