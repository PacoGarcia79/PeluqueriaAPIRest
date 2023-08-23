package com.spring.login.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.spring.login.models.User;
import com.spring.login.payload.response.MessageResponse;
import com.spring.login.security.services.UserService;

@RestController
@RequestMapping(path = "/api/user", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * Este metodo se usa para obtener el listado completo de usuarios
	 * @return
	 */
	@GetMapping("/getUsers")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<User>> getUsers() {
		List<User> usuarios = userService.findAllUsers();

		return usuarios.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para obtener el listado completo de usuarios por rol
	 * @return
	 */
	@GetMapping("/getUsers/{role}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<User>> getUsersByRole(@PathVariable(name = "role") String role) {
		List<User> usuariosByRole = userService.findAllUsersByRole(role);

		return usuariosByRole.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(usuariosByRole, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para modificar datos de un usuario
	 * @param user
	 * @return
	 */
	@PutMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	public ResponseEntity<User> putUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para encontrar usuario por su email
	 * @param email
	 * @return
	 */
	@GetMapping("/{email}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<User> findByEmail(@PathVariable(name = "email") String email) {
		return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para encontrar usuario por su id
	 * @param id
	 * @return
	 */
	@GetMapping("id/{id}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<User> findUserById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para añadir un nuevo usuario a la BBDD
	 * @param user
	 * @return
	 */
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	public ResponseEntity<User> postUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para eliminar un usuario.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	public ResponseEntity<MessageResponse> deleteUsuario(@PathVariable(name = "id") Long id) {

		User user = userService.findUserById(id);

		if (user != null) {
			userService.deleteUser(id);
			return new ResponseEntity<>(new MessageResponse("Borrado correctamente"), 
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse("Error al borrar - No se encuentra el registro"), 
					HttpStatus.CONFLICT);
		}
	}
}
