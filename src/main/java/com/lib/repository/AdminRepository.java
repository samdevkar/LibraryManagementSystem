package com.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
	

}
