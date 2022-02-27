package com.dev.spring.boot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 4, max = 12, message = "El tamaño debe estar entre 4 y 12 caracteres")
	@Column(name = "nombre")
	private String nombre;
	
	@NotEmpty(message = "No puede estar vacio")
	private String apellido;
	
	@NotEmpty(message = "No puede estar vacio")
	@Email(message = "No es una direccion de correo correcta")
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(name = "create_at") // Esta column indicada hace referencia a la BD
	@Temporal(TemporalType.DATE) //Transformar la fecha de java a sql
	@NotNull(message = "No puede estar vacio")
	private Date createAt;
	
	private String foto;
	
	@NotNull(message="la región no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY) // Implementa metodos que no queremos "hibernateLazyInitializer", "handler"
	@JoinColumn(name = "region_id") // Si no lo ponemos, igual lo haria automaticamente cogiendo el nombre_id
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;
	
	//@PrePersist
	//public void prePersist() {
	//	createAt = new Date();
	//}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	
	/**
	 * Getter and Setter de region
	 * Tabla con la que nos conectaremos
	 * a travez de una FK
	 */
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
