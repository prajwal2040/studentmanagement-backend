package com.example.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
    	return repository.findById(id).orElse(null);    
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id,@RequestBody Student updatedStudent) {
    	Student student =repository.findById(id).orElse(null);
    	if(student!=null) {
    		student.setName(updatedStudent.getName());
    		student.setRollNo(updatedStudent.getRollNo());
    		student.setYearOfStudy(updatedStudent.getYearOfStudy());
    		student.setMobileNumber(updatedStudent.getMobileNumber());
    		return repository.save(student);
    	}
    	return null;
    }
    @DeleteMapping("/{id}")
     public String deleteStudent(@PathVariable Integer id){
    	if(repository.existsById(id)) {
    		repository.deleteById(id);
    		return "Student Deleted SUCCESSFULLY";
    	}
    	return "STUDENT NOT FOUND";
    }
    
    
}
