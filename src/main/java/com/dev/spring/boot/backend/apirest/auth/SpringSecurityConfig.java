package com.dev.spring.boot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * Al inyectar con autorwired, se buscara una implementacion con 
	 * UserDetailsService y la unica que cuenta con dicha implementacion 
	 * es usuarioService
	 */
	
	@Autowired
	private UserDetailsService usuarioService;
	
	/**
	 * La forma de registrar objetos que retorna un metodo se hace con la 
	 * notacion @Bean muy parecido a lo que hace @component o @service 
	 * solo que aca lo haces para registrar metodos con @Bean
	 */
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

    @Bean
    public static BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(bcryptPasswordEncoder());
	}
	
	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		/**
		 * Daremos acceso a la API clientes
		 * antMatchers dentro de este metodo iran todas las 
		 * rutas que no necesitan autenticacion
		 */
		http.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.csrf().disable() // Ya que se trabaja front separado del back el csrf no se utilizara
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
}
