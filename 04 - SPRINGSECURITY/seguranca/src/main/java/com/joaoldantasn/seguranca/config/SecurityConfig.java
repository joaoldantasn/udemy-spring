package com.joaoldantasn.seguranca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	
	// role -> grupo de usuario (perfil de usuario) -> Master, gerente, frente de loja, vendedor
	//authority -> permissões -> cadastrar usuario, acessar tela de relatorio

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, SenhaMasterAuthenticationProvider senhaMasterAuthenticationProvider) throws Exception{
		return http
				.authorizeHttpRequests(customizer -> {
					customizer.requestMatchers("/public").permitAll();
	
					//so pode ser chamado por ultimo
					customizer.anyRequest().authenticated();
				})
				.httpBasic(Customizer.withDefaults())
				.authenticationProvider(senhaMasterAuthenticationProvider)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails commonUser = User.builder()
				.username("user")
				.password(passwordEncoder().encode("123"))
				.roles("USER")
				.build();
		
		UserDetails adminUser = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("USER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(commonUser, adminUser);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}
}
