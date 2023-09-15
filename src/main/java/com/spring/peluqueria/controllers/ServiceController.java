package com.spring.peluqueria.controllers;

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

import com.spring.peluqueria.constants.ApiConstants;
import com.spring.peluqueria.models.Service;
import com.spring.peluqueria.payload.response.MessageResponse;
import com.spring.peluqueria.repository.ServiceRepository;

@RestController
@RequestMapping(path = "/api/service", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class ServiceController {

	@Autowired
	ServiceRepository serviceRepository;

	/**
	 * Este metodo se usa para obtener el listado de todos los servicios
	 * @return
	 */
	@GetMapping("/getServices")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Service>> getServices() {
		List<Service> services = serviceRepository.findAll();

		return services.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(services, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para obtener un servicio por su id
	 * @return
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
		Optional<Service> serviceOpt = serviceRepository.findById(id);

		return !serviceOpt.isPresent() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(serviceOpt.get(), HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para añadir un servicio
	 * @param service
	 * @return
	 */
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Service> postService(@RequestBody Service service) {
		return new ResponseEntity<>(serviceRepository.save(service), HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para modificar un servicio existente
	 * @param service
	 * @return
	 */
	@PutMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Service> putService(@RequestBody Service service) {
		return new ResponseEntity<>(serviceRepository.save(service), HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para eliminar un servicio existente
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{idServicio}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteService(@PathVariable(name = "idServicio") Long id) {

		Optional<Service> optionalValue = serviceRepository.findById(id);

		if (optionalValue.isPresent()) {
			serviceRepository.deleteById(id);
			return new ResponseEntity<>(new MessageResponse(ApiConstants.DELETE_OK), 
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse(ApiConstants.DELETE_OK), 
					HttpStatus.CONFLICT);
		}
	}

	
}
