package com.dev.spring.boot.backend.apirest.controller;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.spring.boot.backend.apirest.models.entity.Cliente;
import com.dev.spring.boot.backend.apirest.models.entity.Region;
import com.dev.spring.boot.backend.apirest.models.services.IClienteService;
import com.dev.spring.boot.backend.apirest.models.services.IUploadFileService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		return clienteService.findAll(PageRequest.of(page, 4));
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			/**
			 * Map<String, Object>  ?????
			 */
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
				
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		Cliente clienteNew = null;
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
										.stream()
										.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
										.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente se ha creado con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/**
	 * BindingResult Se utiliza inmeditamente despues de la clase modelo a evaluar
	 * @param cliente
	 * @param result
	 * @param id
	 * @return
	 */
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> update(
			@Valid @RequestBody Cliente cliente, BindingResult result,
			@PathVariable Long id
	) {
		Cliente clienteActual = clienteService.findById(id);
		Map<String, Object> response = new HashMap<>();
		Cliente clienteUpdate = null;
				
		if (clienteActual == null) {
			response.put("error", "Error: no se puede editar, el cliente ID: ".concat(id.toString().concat(" no existe")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
										.stream()
										.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
										.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		try {
			/**
			 * 	Change the information of customer now
			 *  with the information that receive controller
			 */
				clienteActual.setNombre(cliente.getNombre());
				clienteActual.setApellido(cliente.getApellido());
				clienteActual.setCreateAt(cliente.getCreateAt());
				clienteActual.setEmail(cliente.getEmail());
				clienteActual.setRegion(cliente.getRegion());
				clienteUpdate = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente se ha actualizado con exito");
		response.put("cliente", clienteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			Cliente cliente = clienteService.findById(id);
			String nombreArchivoAnterior = cliente.getFoto();
			
			// Eliminar foto
			uploadFileService.eliminar(nombreArchivoAnterior);
			
			// Eliminar cliente
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar delete en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente se ha eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/cliente/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		Cliente cliente = clienteService.findById(id);
		
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadFileService.copiar(archivo);
			} catch (Exception e) {
				response.put("mensaje", "Error al subir el archivo");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreArchivoAnterior = cliente.getFoto();
			
			uploadFileService.eliminar(nombreArchivoAnterior);
			
			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);

			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

		Resource recurso = null;
		try {
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		
		/**
		 * Usar \ en ves de ' comillas simples para procesar la imagen
		 */
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones() {
		return clienteService.findAllRegiones();
	}
}
