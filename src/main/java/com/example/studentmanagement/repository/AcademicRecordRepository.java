package com.example.studentmanagement.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.entity.AcademicRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.entity.AcademicRecord;
public interface AcademicRecordRepository 
	extends JpaRepository<AcademicRecord,Integer>{
	List<AcademicRecord> findByStudentId(Integer studentId);
	
}


