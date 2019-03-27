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
		
		
		 
		 if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	            response.setStatus(HttpServletResponse.SC_OK);
	        }
		 
		// filterChain.doFilter(request, response);
		 else if (request.getHeader("origin")!=null && (request.getHeader("origin").contentEquals("http://dsdiba.demo.in2.es")
				 || request.getHeader("origin").contentEquals("http://localhost:7001")
				 || request.getHeader("origin").contentEquals("http://localhost:8090")
				 || request.getHeader("origin").contentEquals("http://localhost:4200"))) {
			 
			 if (response.getHeader("access-control-allow-origin") == null) {
					response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
				}
			 
			 filterChain.doFilter(request, response);
		 }
		 else { 
	         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        }
	}

}
