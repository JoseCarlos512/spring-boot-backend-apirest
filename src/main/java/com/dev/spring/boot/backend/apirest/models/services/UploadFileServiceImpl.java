package com.dev.spring.boot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Service
 * 	Definir esta clase como un objeto de spring
 *  ya que se guardara en el contenedor de spring
 *  y despues se puede inyectar y utilizar en el controlador
 *
 */

@Service
public class UploadFileServiceImpl implements IUploadFileService{
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	private final static String DIRECTORIO_UPLOAD = "uploads";
	
	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso = null;
		
		// El try-catch no se utiliza porque arriba ya esta throws -> (excepcion)
		//try {
			recurso = new UrlResource(rutaArchivo.toUri());
		//} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("/src/main/resources/static/images").resolve("not-user.png").toAbsolutePath();
			
			// El try-catch no se utiliza porque arriba ya esta throws -> (excepcion)
			//try {
				recurso = new UrlResource(rutaArchivo.toUri());
			//} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			
			log.error("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {

		String nombreArchivo = UUID.randomUUID().toString().concat("_").concat(archivo.getOriginalFilename().replace(" ", ""));

		Path rutaArchivo = getPath(nombreArchivo);
		log.info(rutaArchivo.toString());
		
		Files.copy(archivo.getInputStream(), rutaArchivo);
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if (nombreFoto!=null && nombreFoto.length()>0) {
			Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			File archivoFoto= rutaArchivo.toFile();
			
			if (archivoFoto.exists() && archivoFoto.canRead()) {
				archivoFoto.delete();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
