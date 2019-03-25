package es.in2.dsdibaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@SpringBootApplication
@EnableCaching
@EnableWebMvc
@Slf4j
//@SpringBootTest(classes = DsDibaApiApplicationTests.class)
public class DsDibaApiApplication extends SpringBootServletInitializer implements WebApplicationInitializer,WebMvcConfigurer   {

	public static void main(String[] args) {
		log.info("DsDibaApi - Welcome");
		SpringApplication.run(DsDibaApiApplication.class, args);
	}
	
	
	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(DsDibaApiApplication.class);
	   }
	
	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**").allowedOrigins("http://dsdiba.demo.in2.es", "http://localhost:8090", "http://localhost:7001", "http://localhost:4200").allowedMethods("PUT", "DELETE", "GET", "OPTIONS", "POST");
	    }
	 
	
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {

	        registry.addResourceHandler("swagger-ui.html")
	                 .addResourceLocations("classpath:/META-INF/resources/");

	         registry.addResourceHandler("/webjars/**")
	                 .addResourceLocations("classpath:/META-INF/resources/webjars/");

	 }
	 
	
}
