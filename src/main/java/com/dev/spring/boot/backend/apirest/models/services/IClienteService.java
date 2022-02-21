package com.dev.spring.boot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev.spring.boot.backend.apirest.models.entity.Cliente;
import com.dev.spring.boot.backend.apirest.models.entity.Region;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	/**
	 * Paginator
	 * @param pageable
	 * @return
	 */
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public List<Region> findAllRegiones();
}
