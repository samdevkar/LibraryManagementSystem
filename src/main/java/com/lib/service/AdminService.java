package com.lib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.stereotype.Service;

import com.lib.model.Admin;
import com.lib.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	public void createAdmin(Admin admin) {
		adminRepository.save(admin);
	}
	
	public Admin findAdmin(Integer id) {
		return adminRepository.findById(id).orElse(null);
	}
	public void deleteAdmin(Integer id) {
		adminRepository.deleteById(id);
	}
}
