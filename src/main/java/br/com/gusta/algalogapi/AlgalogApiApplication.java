package br.com.gusta.algalogapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AlgalogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgalogApiApplication.class, args);
	}

}
