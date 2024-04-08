package com.joaoldantasn.seguranca.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		var login = authentication.getName();
		var senha = (String) authentication.getCredentials();
		
		
		String loginMaster = "master";
		String senhaMaster = "@321";
		
		if(loginMaster.equals(login) && senhaMaster.equals(senha)) {
			//tem q ter o prefixo ROLE, porem se for adicionado o grantedAuthorityDefaults não precisa mais
			return new UsernamePasswordAuthenticationToken("Sou Master", null, List.of(new SimpleGrantedAuthority("ADMIN")));
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
