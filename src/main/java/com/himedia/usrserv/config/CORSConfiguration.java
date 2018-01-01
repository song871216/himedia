package com.himedia.usrserv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CORSConfiguration {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(configuration.getAllowedOrigins().toArray(new String[0]))
						.allowedMethods(configuration.getAllowedMethods().toArray(new String[0]))
						.allowCredentials(configuration.getAllowCredentials())
						.maxAge(configuration.getMaxAge());
			}
		};
	}
}
