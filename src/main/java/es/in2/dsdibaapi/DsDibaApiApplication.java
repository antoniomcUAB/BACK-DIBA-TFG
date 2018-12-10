package es.in2.dsdibaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@SpringBootApplication
@Slf4j
public class DsDibaApiApplication {

	public static void main(String[] args) {
		log.info("DsDibaApi - Welcome");
		SpringApplication.run(DsDibaApiApplication.class, args);
	}
}
