package com.example.studentmanagement.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
	List<Certificate> findByStudentId(Integer studentId);

}