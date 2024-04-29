package com.eviden;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Ejercicio274JpaApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
	
	public static void main(String[] args) {
		SpringApplication.run(Ejercicio274JpaApplication.class, args);
	}

	@Configuration
	@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer{
		
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
			.allowedOrigins("http://localhost:4200")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
			.allowCredentials(true);
		}
		
	}
}
