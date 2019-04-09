package es.in2.dsdibaapi.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Aspect
@Configuration
public class DBAspect {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Before("execution(* es.in2.dsdibaapi.repository.*.*(..))")
    public void before() {
		jdbcTemplate.execute("SET ROLE ALL");
	}
}
