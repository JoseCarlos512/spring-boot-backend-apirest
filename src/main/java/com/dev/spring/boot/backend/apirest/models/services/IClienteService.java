package com.dev.spring.boot.backend.apirest.models.services;

import java.util.List;

import com.dev.spring.boot.backend.apirest.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
}
