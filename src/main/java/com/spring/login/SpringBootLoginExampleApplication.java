package com.spring.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.spring.login.config.BezkoderProperties;

@SpringBootApplication
@EnableConfigurationProperties(BezkoderProperties.class)
public class SpringBootLoginExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLoginExampleApplication.class, args);
	}

}