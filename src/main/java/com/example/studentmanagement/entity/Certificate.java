package com.example.studentmanagement.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import com
.fasterxml.jackson.annotation.JsonFormat;	
@Entity
@Table(name="certificate")
public class Certificate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name="student_id")
	private Integer studentId;
	
	@Column(name="certificate_name")
	private String certificateName;
	
	@Column(name="issuing_organization")
	private String issuingOrganization;
	
	
	@Column(name="certificate_level")
	
	private String certificateLevel;


	@Column(name="certificate_file")
	private String certificateFile;
	
	public Certificate() {	
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
	
	
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName( String certificateName) {
		this.certificateName=certificateName;
	}
	
	public String getIssuingOrganization() {
		return issuingOrganization;
	}
	public  void setIssuingOrganization(String issuingOrganization) {
		this.issuingOrganization=issuingOrganization;
	}
	
	
	
	public String getCertificateLevel() {
		return certificateLevel;
	}
	public void setCertificateLevel( String certificateLevel) {
		this.certificateLevel=certificateLevel;
	}
	
	public String getCertificateFile() {
		return certificateFile;
	}
	public void setCertificateFile(String certificateFile) {
		this.certificateFile=certificateFile;
	}
}
