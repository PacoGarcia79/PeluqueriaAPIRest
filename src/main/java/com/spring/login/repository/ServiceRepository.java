package com.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.login.models.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
