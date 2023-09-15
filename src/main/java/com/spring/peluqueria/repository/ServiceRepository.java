package com.spring.peluqueria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.peluqueria.models.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
