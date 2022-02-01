package com.dev.spring.boot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.dev.spring.boot.backend.apirest.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
