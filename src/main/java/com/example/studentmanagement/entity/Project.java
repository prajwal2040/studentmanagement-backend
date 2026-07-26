package com.example.studentmanagement.entity;
import jakarta.persistence.*;
@Entity
@Table(name="project")
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="student_id")
	private Integer studentId;
	
	@Column(name="github_link")
	private String githubLink;
	
	@Column(name="project_type")
	private String projectType;
	
	public Project() {
		
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
	
	
	public String getGithubLink() {
		return githubLink;
	}
	public void setGithubLink(String githubLink) {
		this.githubLink=githubLink;
	}
	
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType=projectType;
	}
}
