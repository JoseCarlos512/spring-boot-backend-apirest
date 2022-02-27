package com.dev.spring.boot.backend.apirest.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/**
		 * Daremos acceso a la API clientes
		 * antMatchers dentro de este metodo iran todas las 
		 * rutas que no necesitan autenticacion
		 */
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes")
		.permitAll()
		.anyRequest()
		.authenticated();
	}
	
}
