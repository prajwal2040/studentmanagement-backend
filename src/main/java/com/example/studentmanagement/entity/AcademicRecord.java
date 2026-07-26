package com.example.studentmanagement.entity;
import jakarta.persistence.*;
@Entity 
@Table(name="academic_record")
public class AcademicRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="student_id")
	private Integer studentId;
	private Integer semester;
	private Double sgpa;
	private Double cgpa;
	@Column(name="backlog_count")
	private Integer backlogCount;
	
	public AcademicRecord() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getstudentId() {
		return studentId;
	}
	public void setstudentId(Integer studentId) {
		this.studentId=studentId;
	}
	public Integer getsemester() {
		return semester;
	}
	public void setsemester(Integer semester) {
		this.semester=semester;
	}
	public Double getsgpa() {
		return sgpa;
	}
	public void setsgpa(Double sgpa) {
		this.sgpa=sgpa;
	}
	public Double getcgpa() {
		return cgpa;
	}
	public void setcgpa(Double cgpa) {
		this.cgpa=cgpa;
	}
	public Integer getbacklogcount() {
		return backlogCount;
	}
	public void setbacklogcount(Integer backlogCount) {
		this.backlogCount=backlogCount;
	}
}
