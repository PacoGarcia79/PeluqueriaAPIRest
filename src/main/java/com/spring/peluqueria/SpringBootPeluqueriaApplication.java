package com.spring.peluqueria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.spring.peluqueria.config.BezkoderProperties;

@SpringBootApplication
@EnableJpaRepositories("com.spring.peluqueria.repository")
@EnableConfigurationProperties(BezkoderProperties.class)
public class SpringBootPeluqueriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPeluqueriaApplication.class, args);
	}

}
