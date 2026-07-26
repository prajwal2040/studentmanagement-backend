package com.example.studentmanagement.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	List<Attendance> findByStudentId(Integer studentId);

}