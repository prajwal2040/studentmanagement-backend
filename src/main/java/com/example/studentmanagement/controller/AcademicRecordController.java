package com.example.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.entity.AcademicRecord;
import com.example.studentmanagement.repository.AcademicRecordRepository;

@RestController
@RequestMapping("/academic-records")
@CrossOrigin(origins = "*")
public class AcademicRecordController {

    @Autowired
    private AcademicRecordRepository repository;

    // Add Academic Record
    @PostMapping
    public AcademicRecord addAcademicRecord(@RequestBody AcademicRecord record) {
        return repository.save(record);
    }

    // Get All Academic Records
    @GetMapping
    public List<AcademicRecord> getAllRecords() {
        return repository.findAll();
    }

    // Get Academic Record By ID
    @GetMapping("/{id}")
    public AcademicRecord getAcademicRecordById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void dleteAcademicRecord(@PathVariable int id) {
    	repository.deleteById(id);
    }
    @PutMapping("/{id}")
    public AcademicRecord updateAcademicRecord(@PathVariable int id,@RequestBody AcademicRecord updatedRecord) {
    	AcademicRecord record= repository.findById(id).orElse(null);
    	if(record!=null) {
    		record.setstudentId(updatedRecord.getstudentId());
    		record.setsemester(updatedRecord.getsemester());
    		record.setsgpa(updatedRecord.getsgpa());
    		record.setcgpa(updatedRecord.getcgpa());
    		record.setbacklogcount(updatedRecord.getbacklogcount());
    		return repository.save(record);
    	}
    	return null;
    }
    
    @GetMapping("/student/{studentId}")
    public List<AcademicRecord> getAcademicRecordsByStudentId(@PathVariable Integer studentId) {
        return repository.findByStudentId(studentId);
    }
    
    
    
    
    
    
}