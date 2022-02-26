package com.dev.spring.boot.backend.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.spring.boot.backend.apirest.models.dao.IUsuarioDao;
import com.dev.spring.boot.backend.apirest.models.entity.Usuario;

/**
 * Implementar una interfaz, que la provee "spring security"
 * para trabajar con JPA para realizar el proceso de autenticacion
 */

@Service
public class UsuarioService implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	/**
	 * Autowired para inyectar muy importante no olvidar
	 */
	@Autowired
	private IUsuarioDao usuarioDao;
	
	/**
	 * UserDetails -> este es el usuario de springscurity
	 * para usar el mismo usuario o tipado debemos retornar
	 * uno de mismo tipo que es new User
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if (usuario == null) {
			logger.error("Error: no existe el usuario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException("Error: no existe el usuario '" + username + "' en el sistema!");
		}
		
		/**
		 * No entendi mucho a revisar denuevo
		 */
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
