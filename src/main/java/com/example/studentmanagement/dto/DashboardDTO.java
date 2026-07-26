package com.example.studentmanagement.dto;

public class DashboardDTO {

    private Integer studentId;
    private String studentName;
    private Double cgpa;
    private Double attendancePercentage;
    private Integer certificateCount;
    private Integer githubProjectCount;
    private Double overallScore;
    private Integer rank;
    private String placementStatus;

    public DashboardDTO() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public Integer getCertificateCount() {
        return certificateCount;
    }

    public void setCertificateCount(Integer certificateCount) {
        this.certificateCount = certificateCount;
    }

    public Integer getGithubProjectCount() {
        return githubProjectCount;
    }

    public void setGithubProjectCount(Integer githubProjectCount) {
        this.githubProjectCount = githubProjectCount;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(String placementStatus) {
        this.placementStatus = placementStatus;
    }
}