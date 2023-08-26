package com.spring.login.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.login.models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query(value = "CALL testdb.noavailabilitydates();", nativeQuery = true)
	List<List<Object>> findNoAvailabilityDates();

	@Query(value = "CALL testdb.addnoavailability(:fechaComienzo, :fechaFin, :empleados, :horas);", nativeQuery = true)
	int addNoAvailability(@Param("fechaComienzo") Date fechaComienzo, @Param("fechaFin") Date fechaFinal,
			@Param("empleados") String empleados, @Param("horas") String horarios);
	
	@Query(value = "CALL testdb.delnoavailability(:fechaComienzo, :fechaFin, :empleados, :horas);", nativeQuery = true)
	int delNoAvailability(@Param("fechaComienzo") Date fechaComienzo, @Param("fechaFin") Date fechaFinal,
			@Param("empleados") String empleados, @Param("horas") String horarios);
	
	@Query(value = "CALL testdb.deldisponibilidadporid(:ids);", nativeQuery = true)
	int delNoAvailabilityById(@Param("ids") String ids);
	
	@Query(value = "CALL testdb.horarioslibresempleadosfecha(:id,:date);", nativeQuery = true)
	List<List<Object>> findEmployeeFreeScheduleByDate(@Param("id") Long idUsuario, @Param("date") Date date);

	@Query(value = "CALL testdb.horarioslibresdia(:date);", nativeQuery = true)
	List<List<Object>> findFreeScheduleByDate(@Param("date") Date date);

}
