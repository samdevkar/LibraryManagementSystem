package com.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lib.dto.CreateSearchRequest;
import com.lib.dto.CreateStudentRequest;
import com.lib.dto.UpdateStudentRequest;
import com.lib.model.NewStudent;
import com.lib.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@PostMapping("/createstudent")
	public void createStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest) {
		studentService.createStudent(createStudentRequest.toStudent());
	}
	@PutMapping("/updatestudent/{id}")
	public void updateStudentRequest(@PathVariable Integer id,@RequestBody @Valid UpdateStudentRequest updateStudentRequest) {
		studentService.updateStudent(id,updateStudentRequest.toUpdateStudent());
	}
	@GetMapping("/getAllStudent")
	public List<NewStudent>findAllStudent(){
		return studentService.findAllStudent();
	}
	@GetMapping("/getStudent")
	public List<NewStudent>findStudent(@RequestBody CreateSearchRequest createSearchRequest) throws Exception{
		return studentService.findStudent(createSearchRequest.getSearchKey(),createSearchRequest.getSearchValue());
	}
	@DeleteMapping("/deleteStudent/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		studentService.deleteStudent(id);
	}
}
