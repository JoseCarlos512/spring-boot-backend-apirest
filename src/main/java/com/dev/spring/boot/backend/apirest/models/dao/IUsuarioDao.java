package com.dev.spring.boot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.dev.spring.boot.backend.apirest.models.entity.Usuario;

/**
 * Si queremos implementar la paginacion aplicada en la lista de clientes
 * debemos extender de jparepository que tiene esta funcionalidad implementada
 * ademas tbm cuenta con una extencion de crudrepository
 */
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	/**
	 * Tener mas de un filtro
	 * public Usuario findByUsernameAndEmail(String username, String email);
	 * 
	 * Ademas podemos realizar la peticion con @query
	 * 
	 * @Query("select u from Usuario u where u.username = ?1 and u.email = ?2")
	 * public Usuario findByUsername(String username, String email)
	 */
	
	public Usuario findByUsername(String username);
}
