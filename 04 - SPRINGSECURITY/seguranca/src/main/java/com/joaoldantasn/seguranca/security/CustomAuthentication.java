package com.joaoldantasn.seguranca.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@SuppressWarnings("serial")
public class CustomAuthentication implements Authentication{
	
	private final IdentificacaoUsuario identificacaoUsuario;
	

	public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
		
		if(identificacaoUsuario == null) {
			throw new ExceptionInInitializerError("Não é possivel criar um customAuthentication sem a identificação do usuário");
		}
		
		this.identificacaoUsuario = identificacaoUsuario;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.identificacaoUsuario.getNome();
	}

	@Override
	//roles
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.identificacaoUsuario
				.getPermissoes()
				.stream()
				.map(perm -> new SimpleGrantedAuthority(perm))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.identificacaoUsuario;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		throw new IllegalArgumentException("Não precisa chamar, já estamos autenticados");
		
	}

}
