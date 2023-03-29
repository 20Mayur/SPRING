package com.Springboot.MySql.Dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Springboot.MySql.Entities.CourseModel;

public interface CourseDao extends JpaRepository<CourseModel, Long> {

	List<CourseModel> findByUuid(UUID uuid);

	@Query(value = "SELECT * FROM book WHERE uuid = :uuid ORDER BY created_on DESC LIMIT 1", nativeQuery = true)
    public CourseModel getlatestVersion(@Param("uuid")UUID uuid);
	
	
     Optional<CourseModel> findByEmail(String email);

      

}
