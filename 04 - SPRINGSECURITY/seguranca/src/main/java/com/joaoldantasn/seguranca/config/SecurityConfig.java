package com.joaoldantasn.seguranca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.authorizeHttpRequests(customizer -> {
					customizer.requestMatchers("/public").permitAll();
					
					//so pode ser chamado por ultimo
					customizer.anyRequest().authenticated();
				})
				.httpBasic(Customizer.withDefaults())
				.build();
	}
	
}
