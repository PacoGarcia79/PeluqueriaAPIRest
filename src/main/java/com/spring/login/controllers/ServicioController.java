package com.spring.login.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.Servicio;
import com.spring.login.payload.response.MessageResponse;
import com.spring.login.repository.ServicioRepository;

@RestController
@RequestMapping(path = "/api/servicio", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class ServicioController {

	@Autowired
	ServicioRepository servicioRepository;

	/**
	 * Este metodo se usa para obtener el listado de todos los servicios
	 * @return
	 */
	@GetMapping("/getServicios")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Servicio>> getServicios() {
		List<Servicio> servicios = servicioRepository.findAll();

		return servicios.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(servicios, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para obtener un servicio por su id
	 * @return
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
		Optional<Servicio> servicioOpt = servicioRepository.findById(id);

		return !servicioOpt.isPresent() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(servicioOpt.get(), HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para añadir un servicio
	 * @param servicio
	 * @return
	 */
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Servicio> postServicio(@RequestBody Servicio servicio) {
		return new ResponseEntity<>(servicioRepository.save(servicio), HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para modificar un servicio existente
	 * @param servicio
	 * @return
	 */
	@PutMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Servicio> putServicio(@RequestBody Servicio servicio) {
		return new ResponseEntity<>(servicioRepository.save(servicio), HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para eliminar un servicio existente
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{idServicio}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteServicio(@PathVariable(name = "idServicio") Long id) {

		Optional<Servicio> optionalValue = servicioRepository.findById(id);

		if (optionalValue.isPresent()) {
			servicioRepository.deleteById(id);
			return new ResponseEntity<>(new MessageResponse("Borrado correctamente"), 
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse("Error al borrar - No se encuentra el registro"), 
					HttpStatus.CONFLICT);
		}
	}

}
