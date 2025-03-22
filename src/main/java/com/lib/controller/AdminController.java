package com.lib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lib.dto.CreateAdminRequest;
import com.lib.service.AdminService;

import jakarta.validation.Valid;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/createadmin")
	public void createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
		
		adminService.createAdmin(createAdminRequest.toAdmin());
		
	}
	@DeleteMapping("deleteadmin/{id}")
	public void deleteAdmin(@PathVariable Integer id) {
		adminService.deleteAdmin(id);
	}
	

}
