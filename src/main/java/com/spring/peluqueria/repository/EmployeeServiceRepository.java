package com.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.login.models.EmployeeService;

public interface EmployeeServiceRepository extends JpaRepository<EmployeeService, Long> {

}
