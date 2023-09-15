package com.spring.peluqueria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.peluqueria.models.EmployeeService;

public interface EmployeeServiceRepository extends JpaRepository<EmployeeService, Long> {

}
