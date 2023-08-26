package com.spring.login.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.login.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "select u.id, u.nombre, u.apellidos, u.username, u.email, u.telefono, u.fecha_alta, "
			+ "u.fecha_baja, u.foto, u.password from users u inner join user_roles ur on u.id = ur.user_id "
			+ "inner join roles r on r.id = ur.role_id where r.name = :role and u.fecha_baja is null;", nativeQuery = true)
	List<User> findUsersByRole(@Param("role") String role);
	
	User findByEmail(@Param("email") String email);
	
	User findUserById(@Param("id") Long id);
	
	@Query(value = "CALL testdb.addhorariosuser();", nativeQuery = true)	
	Long addHorariosToUser();
	
	@Query(value = "CALL testdb.listaempleadosfaltaservicio(:idServicio);", nativeQuery = true)
	List<User> findUsersWithoutService(@Param("idServicio") Long idServicio);
	
	@Query(value = "CALL testdb.empleadosdisponiblesfechahora(:id,:date);", nativeQuery = true)
	List<List<Object>> findEmployeesAvailableByDateTime(@Param("id") Long idTime, @Param("date") Date date);

	@Query(value = "CALL testdb.empleadosdisponiblesfecha(:date);", nativeQuery = true)
	List<List<Object>> findEmployeesAvailableByDate(@Param("date") Date date);
}