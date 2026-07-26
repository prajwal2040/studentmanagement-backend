package com.example.studentmanagement.entity;
import jakarta.persistence.*;
@Entity 
@Table(name="attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="student_id")
	private Integer studentId;
	private String month;
	private String subject;
	@Column(name="total_classes")
	private Integer total_classes;
	@Column(name="attended_classes")
	private Integer attended_classes;
	@Column(name="attendance_percentage")
	private Double attendance_percentage;
	
	public Attendance() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId=studentId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month=month;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject=subject;
	}
	public Integer getTotalClasses() {
		return total_classes;
	}
	public void setTotalClasses(Integer total_classes) {
		this.total_classes=total_classes;
	}
	public Integer getAttendedClasses() {
		return attended_classes;
	}
	public void setAttendedClasses(Integer attended_classes) {
		this.attended_classes=attended_classes;
	}
	public Double getAttendancePercentage() {
		return attendance_percentage;
	}
	public void setAttendancePercentage(Double attendance_percentage) {
		this.attendance_percentage=attendance_percentage;
	}
}
