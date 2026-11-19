package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.LeetCodeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeetCodeProfileRepository extends JpaRepository<LeetCodeProfile, Integer> {

    Optional<LeetCodeProfile> findByStudentId(Integer studentId);
}