package com.example.studentmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.entity.Attendance;
import com.example.studentmanagement.repository.AttendanceRepository;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceRepository repository;

    // POST API
    @PostMapping
    public Attendance addAttendance(@RequestBody Attendance attendance) {

        double percentage =
                (attendance.getAttendedClasses() * 100.0)
                / attendance.getTotalClasses();

        attendance.setAttendancePercentage(percentage);

        return repository.save(attendance);
    }

    // GET ALL API
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return repository.findAll();
    }

    // GET BY ID API
    @GetMapping("/{id}")
    public Optional<Attendance> getAttendanceById(@PathVariable Integer id) {
        return repository.findById(id);
    }
    
    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudentId(@PathVariable Integer studentId) {

        return repository.findByStudentId(studentId);

    }

    // UPDATE API
    @PutMapping("/{id}")
    public Attendance updateAttendance(@PathVariable Integer id,
                                       @RequestBody Attendance attendance) {
        attendance.setId(id);

        double percentage =
                (attendance.getAttendedClasses() * 100.0)
                / attendance.getTotalClasses();

        attendance.setAttendancePercentage(percentage);

        return repository.save(attendance);
    }

    // DELETE API
    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Integer id) {

        repository.deleteById(id);

        return "Attendance deleted successfully.";
    }

}