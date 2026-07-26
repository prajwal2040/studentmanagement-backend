package com.example.studentmanagement.service;

import java.util.List;
import java.util.ArrayList;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dto.DashboardDTO;
import com.example.studentmanagement.entity.AcademicRecord;
import com.example.studentmanagement.entity.Attendance;
import com.example.studentmanagement.entity.Certificate;
import com.example.studentmanagement.entity.Project;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.AcademicRecordRepository;
import com.example.studentmanagement.repository.AttendanceRepository;
import com.example.studentmanagement.repository.CertificateRepository;
import com.example.studentmanagement.repository.ProjectRepository;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private ProjectRepository projectRepository;
    
    public DashboardDTO getDashboard(Integer studentId) {

        DashboardDTO dashboard = new DashboardDTO();

        // Student
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isEmpty()) {
            return null;
        }

        Student student = optionalStudent.get();

        dashboard.setStudentId(student.getId());
        dashboard.setStudentName(student.getName());

        // Academic
        List<AcademicRecord> academicList =
                academicRecordRepository.findByStudentId(studentId);

        if (!academicList.isEmpty()) {
            AcademicRecord latestAcademic =
                    academicList.get(academicList.size() - 1);

            dashboard.setCgpa(latestAcademic.getcgpa());
        }

        // Attendance
        List<Attendance> attendanceList =
                attendanceRepository.findByStudentId(studentId);

        if (!attendanceList.isEmpty()) {

            double total = 0;

            for (Attendance attendance : attendanceList) {
                total += attendance.getAttendancePercentage();
            }

            dashboard.setAttendancePercentage(
                    total / attendanceList.size());
        }

        // Certificates
        List<Certificate> certificateList =
                certificateRepository.findByStudentId(studentId);

        dashboard.setCertificateCount(certificateList.size());

        // GitHub Projects
        List<Project> projectList =
                projectRepository.findByStudentId(studentId);

        dashboard.setGithubProjectCount(projectList.size());
        double academicScore =
                calculateAcademicScore(dashboard.getCgpa());

        double attendanceScore =
                calculateAttendanceScore(dashboard.getAttendancePercentage());

        double certificateScore =
                calculateCertificateScore(dashboard.getCertificateCount());

        double githubScore =
                calculateGithubScore(dashboard.getGithubProjectCount());

        double totalScore =
                academicScore
              + attendanceScore
              + certificateScore
              + githubScore;

        dashboard.setOverallScore(totalScore);
        dashboard.setRank(calculateRank(dashboard.getOverallScore()));
        dashboard.setPlacementStatus(
                calculatePlacementStatus(totalScore));

        dashboard.setRank(
                calculateRank(totalScore));

        return dashboard;
    }
    
    private double calculateAcademicScore(double cgpa) {
        return (cgpa / 10.0) * 40.0;
    }

    private double calculateAttendanceScore(double attendance) {

        if (attendance >= 95)
            return 20;

        else if (attendance >= 90)
            return 18;

        else if (attendance >= 85)
            return 16;

        else if (attendance >= 80)
            return 14;

        else if (attendance >= 75)
            return 10;

        return 0;
    }

    private double calculateCertificateScore(int certificates) {

        double score = certificates * 2;

        return Math.min(score, 20);
    }

    private double calculateGithubScore(int projects) {

        double score = projects * 2;

        return Math.min(score, 20);
    }
    
    
    
    private double calculateOverallScore(Integer studentId) {

        double cgpa = 0;
        double attendance = 0;
        int certificateCount = 0;
        int projectCount = 0;

        // Academic
        List<AcademicRecord> academicList =
                academicRecordRepository.findByStudentId(studentId);

        if (!academicList.isEmpty()) {
            cgpa = academicList.get(academicList.size() - 1).getcgpa();
        }

        // Attendance
        List<Attendance> attendanceList =
                attendanceRepository.findByStudentId(studentId);

        if (!attendanceList.isEmpty()) {

            double total = 0;

            for (Attendance a : attendanceList) {
                total += a.getAttendancePercentage();
            }

            attendance = total / attendanceList.size();
        }

        // Certificate
        certificateCount =
                certificateRepository.findByStudentId(studentId).size();

        // Projects
        projectCount =
                projectRepository.findByStudentId(studentId).size();

        return calculateAcademicScore(cgpa)
             + calculateAttendanceScore(attendance)
             + calculateCertificateScore(certificateCount)
             + calculateGithubScore(projectCount);
    }
    
    private int calculateRank(double currentStudentScore) {

        List<Student> students = studentRepository.findAll();

        int rank = 1;

        for (Student student : students) {

            double score = calculateOverallScore(student.getId());

            if (score > currentStudentScore) {
                rank++;
            }
        }

        return rank;
    }
    
    private String calculatePlacementStatus(double overallScore) {

        if (overallScore >= 80) {
            return " Green  - Placement Ready";
        }
        else if (overallScore >= 60) {
            return "🟡 Yellow - Needs Improvement";
        }
        else {
            return "🔴 Red- High Priority Improvement";
        }
    }
    
    public List<DashboardDTO> getAllDashboard() {

        List<Student> students = studentRepository.findAll();

        List<DashboardDTO> dashboardList = new ArrayList<>();

        for(Student student : students) {

            DashboardDTO dashboard = getDashboard(student.getId());

            dashboardList.add(dashboard);
        }

        // Sort according to overall score (highest first)
        dashboardList.sort((a,b) ->
            Double.compare(b.getOverallScore(), a.getOverallScore())
        );


        // Assign rank
        int rank = 1;

        for(DashboardDTO dashboard : dashboardList) {

            dashboard.setRank(rank);
            rank++;
        }


        return dashboardList;
    }
    
    

}