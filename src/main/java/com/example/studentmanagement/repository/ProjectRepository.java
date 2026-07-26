package com.example.studentmanagement.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	List<Project> findByStudentId(Integer studentId);

}