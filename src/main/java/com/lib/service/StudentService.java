package com.lib.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.model.NewStudent;
import com.lib.repository.NewStudentRepository;

@Service
public class StudentService {

	@Autowired
	private NewStudentRepository newStudentRepository;
	
	public void createStudent(NewStudent newStudent) {
		newStudentRepository.save(newStudent);
	}
	
	public void updateStudent(Integer id,NewStudent newStudent) {
		
		Optional<NewStudent>student=newStudentRepository.findById(id);
		if(student.isPresent()) {
			NewStudent newStudent2=student.get();
			newStudent2.setEmail(newStudent.getEmail());
			newStudent2.setName(newStudent.getName());
			newStudentRepository.save(newStudent2);
		}
		
	}
	
	public  List<NewStudent>findStudent(String searchKey,String searchValue) throws Exception {
		switch (searchKey) {
			case "name": {
				return newStudentRepository.findByName(searchValue);
			}
			case "rollNumber": {
			    return newStudentRepository.findByRollNumber(searchValue);
			}
	        case "email": {
				return newStudentRepository.findByEmail(searchValue);
			}
			case "id": {
				Optional<NewStudent>student= newStudentRepository.findById(Integer.parseInt(searchValue));
				return student.map(Arrays::asList).orElseGet(ArrayList::new);
			}
			default:
				throw new Exception("Unexpected value: " + searchKey);
			}
	}
	public void deleteStudent(Integer id) {
		newStudentRepository.deleteById(id);
	}
	
	public List<NewStudent>findAllStudent(){
		return newStudentRepository.findAll();
	}
}
