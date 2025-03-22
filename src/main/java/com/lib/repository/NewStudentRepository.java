package com.lib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lib.model.NewStudent;

public interface NewStudentRepository extends JpaRepository<NewStudent,Integer> {

	@Query("from NewStudent where name=:name")
	public List<NewStudent> findByName(@Param("name") String name);

	@Query("from NewStudent where rollNumber=:number")
	public List<NewStudent> findByRollNumber(String number);

	@Query("from NewStudent where email=:email")
	public List<NewStudent> findByEmail(@Param("email") String email);
	

}
