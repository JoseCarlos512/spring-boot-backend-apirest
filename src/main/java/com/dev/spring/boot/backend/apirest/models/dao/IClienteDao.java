package com.dev.spring.boot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.spring.boot.backend.apirest.models.entity.Cliente;
import com.dev.spring.boot.backend.apirest.models.entity.Region;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
	
	@Query("FROM Region") // Region nombre de la clase
	public List<Region> findAllRegiones();
}
