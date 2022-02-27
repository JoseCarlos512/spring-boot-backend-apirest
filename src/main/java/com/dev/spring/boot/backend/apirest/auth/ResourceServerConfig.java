package com.dev.spring.boot.backend.apirest.auth;

import java.util.Arrays;

import org.aspectj.weaver.ast.And;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**").permitAll()
		/*.antMatchers(HttpMethod.GET, "/api/cliente/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/cliente/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/cliente").hasRole("ADMIN")
		.antMatchers("/api/cliente/**").hasRole("ADMIN")*/
		.anyRequest()
		.authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		/**
		 * Configurar nuestros dominios, que es nuestra aplicacion angular
		 */
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		
		/**
		 * Configurara permitir credenciales y Headers
		 */
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		/**
		 * 
		 */
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	/**
	 * Congimos toda la configuracion que se tenia en el anterior metodo y se 
	 * Regitro dentro del stack del conjunto de filtros que maneja spring framework
	 * y se le dio una prioridad ALTA
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
