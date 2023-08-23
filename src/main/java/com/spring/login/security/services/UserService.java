package com.spring.login.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.User;
import com.spring.login.repository.UserRepository;

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
}
