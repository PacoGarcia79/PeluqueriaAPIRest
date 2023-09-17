package com.spring.peluqueria.controllers;

import java.sql.Date;
import java.util.List;

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

import com.spring.peluqueria.constants.ApiConstants;
import com.spring.peluqueria.models.User;
import com.spring.peluqueria.payload.response.MessageResponse;
import com.spring.peluqueria.services.UserService;

@RestController
@RequestMapping(path = "/api/user", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * Este metodo se usa para obtener el listado completo de usuarios
	 * 
	 * @return
	 */
	@GetMapping("/getUsers")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.findAllUsers();

		return users.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(users, HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para obtener el listado completo de usuarios por rol
	 * 
	 * @return
	 */
	@GetMapping("/getUsers/{role}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<User>> getUsersByRole(@PathVariable(name = "role") String role) {
		List<User> usersByRole = userService.findAllUsersByRole(role);

		return usersByRole.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(usersByRole, HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para modificar datos de un usuario
	 * 
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
	 * 
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
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("id/{id}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<User> findUserById(@PathVariable(name = "id") Long id) {
		User user = userService.findUserById(id);
		return user == null ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para añadir un nuevo usuario a la BBDD
	 * 
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
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	public ResponseEntity<MessageResponse> deleteUser(@PathVariable(name = "id") Long id) {

		User user = userService.findUserById(id);

		if (user != null) {
			userService.deleteUser(id);
			return new ResponseEntity<>(new MessageResponse(ApiConstants.DELETE_OK), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse(ApiConstants.DELETE_OK),
					HttpStatus.CONFLICT);
		}
	}

	/**
	 * Este metodo se usa para añadir los horarios a cada empleado.
	 * @return
	 */
	@PutMapping("/schedule")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')") 
	public ResponseEntity<Boolean> addHorariosToUser() {
		return new ResponseEntity<>(userService.addHorariosToUser(), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para obtener el listado de empleados que no tengan
     * asignado un servicio determinado.
	 * @param idServicio
	 * @return
	 */
	@GetMapping("noService/{idServicio}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')") 
	@ResponseBody
	public ResponseEntity<List<User>> findUsersWithoutService(@PathVariable(name = "idServicio") Long idServicio) {
		return new ResponseEntity<>(userService.findUsersWithoutService(idServicio), HttpStatus.OK);
	}
	
	
	/**
	 * Este metodo se usa para obtener el listado de empleados libres en una fecha y horario determinado
	 * @param idTime
	 * @param date
	 * @return
	 */
	@GetMapping("/employeeAvailableByDateTime/{time}/{date}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<User>> getEmployeesAvailableByDateTime(@PathVariable(name = "time") Long idTime, @PathVariable(name = "date") Date date){
		List<User> employeesAvailable = userService.findEmployeesAvailableByDateTime(idTime, date);
		
		return employeesAvailable.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(employeesAvailable, HttpStatus.OK); 
	}
	
	/**
	 * Este metodo se usa para obtener el listado de empleados libres en una fecha determinada
	 * @param date
	 * @return
	 */
	@GetMapping("/employeeAvailableByDate/{date}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<User>> getEmployeesAvailableByDate(@PathVariable(name = "date") Date date){
		List<User> employeesAvailable = userService.findEmployeesAvailableByDate(date);
		
		return employeesAvailable.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(employeesAvailable, HttpStatus.OK); 
	}
}
