package com.spring.peluqueria.services;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.peluqueria.models.User;
import com.spring.peluqueria.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public List<User> findAllUsersByRole(String role) {
		return userRepository.findUsersByRole(role);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findUserById(Long id) {
		return userRepository.findUserById(id);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public Boolean addHorariosToUser() {
		Long maxId = userRepository.addHorariosToUser();
		return maxId != 0;
	}
	
	public List<User> findUsersWithoutService(Long idServicio){
		return userRepository.findUsersWithoutService(idServicio);
	}
	

	public List<User> findEmployeesAvailableByDateTime(Long idTime, Date date) {
		List<List<Object>> employeesAvailableByDateTimeObj = userRepository.findEmployeesAvailableByDateTime(idTime, date);
		List<User> employeesAvailableByDateTime = new ArrayList<>();
		for (List<Object> rawData : employeesAvailableByDateTimeObj) {
			User user = new User();
			user.setId(((BigInteger) rawData.get(0)).longValue());
			user.setNombre((String) rawData.get(1));
			user.setApellidos((String) rawData.get(2));
			user.setUsername((String) rawData.get(3));
			user.setEmail((String) rawData.get(4));
			user.setTelefono((String) rawData.get(5));
			user.setFechaAlta((Date) rawData.get(6));
			user.setFoto((String) rawData.get(8));
			user.setPassword((String) rawData.get(9));
			employeesAvailableByDateTime.add(user);
		}		
		
		return employeesAvailableByDateTime;
	}
	
	public List<User> findEmployeesAvailableByDate(Date date) {
		List<List<Object>> employeesAvailableByDateObj = userRepository.findEmployeesAvailableByDate(date);
		List<User> employeesAvailableByDate = new ArrayList<>();
		for (List<Object> rawData : employeesAvailableByDateObj) {
			User user = new User();
			user.setId(((BigInteger) rawData.get(0)).longValue());
			user.setNombre((String) rawData.get(1));
			user.setApellidos((String) rawData.get(2));
			user.setUsername((String) rawData.get(3));
			user.setEmail((String) rawData.get(4));
			user.setTelefono((String) rawData.get(5));
			user.setFechaAlta((Date) rawData.get(6));
			user.setFoto((String) rawData.get(8));
			user.setPassword((String) rawData.get(9));
			employeesAvailableByDate.add(user);
		}		
		
		return employeesAvailableByDate;
	}
}
