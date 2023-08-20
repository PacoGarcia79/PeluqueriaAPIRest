package com.spring.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.login.models.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long>{
	
	@Query(value = "CALL testdb.noavailabilitydates();",nativeQuery = true)
	List<List<Object>> findNoAvailabilityDates();

}
