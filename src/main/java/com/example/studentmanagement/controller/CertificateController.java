package com.example.studentmanagement.controller;
import com.example.studentmanagement.service.GeminiCertificateService;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.studentmanagement.dto.CertificateGeminiResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.studentmanagement.entity.Certificate;
import com.example.studentmanagement.repository.CertificateRepository;


@RestController
@RequestMapping("/certificate")
@CrossOrigin(origins = "*")
public class CertificateController {
	@Autowired
	private ObjectMapper objectMapper;

    @Autowired
    private CertificateRepository repository;


    @Autowired
    private GeminiCertificateService geminiCertificateService;
    
    
    @PostMapping("/gemini-upload")
    public Certificate uploadAndSaveCertificate(
            @RequestParam("file") MultipartFile file,
            @RequestParam("studentId") Integer studentId) {

        Path tempFile = null;

        try {

            tempFile = Files.createTempFile(
                    "certificate-",
                    "-" + file.getOriginalFilename()
            );

            file.transferTo(tempFile.toFile());

            // Gemini extracts certificate details
            String result =
                    geminiCertificateService.extractCertificateDetails(tempFile);

            // Convert Gemini JSON into Java object
            CertificateGeminiResponse response =
                    objectMapper.readValue(
                            result,
                            CertificateGeminiResponse.class
                    );

            // Create Certificate entity
            Certificate certificate = new Certificate();

            certificate.setStudentId(studentId);
            certificate.setCertificateName(
                    response.getCertificateName()
            );
            certificate.setIssuingOrganization(
                    response.getIssuingOrganization()
            );
            certificate.setCourseDuration(
                    response.getCourseDuration()
            );

            // Convert Gemini date to LocalDate
            if (response.getIssueDate() != null
                    && !response.getIssueDate().equalsIgnoreCase("N/A")) {

                certificate.setIssueDate(
                        LocalDate.parse(response.getIssueDate())
                );

            } else {

                certificate.setIssueDate(null);
            }

            // Save into MySQL
            return repository.save(certificate);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Certificate processing failed: " + e.getMessage()
            );

        } finally {

            if (tempFile != null) {

                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostMapping("/gemini-test")
    public String testGeminiCertificate(
            @RequestParam("file") MultipartFile file) {

        try {

            Path tempFile = Files.createTempFile(
                    "certificate-",
                    "-" + file.getOriginalFilename()
            );

            file.transferTo(tempFile.toFile());

            String result =
                    geminiCertificateService.extractCertificateDetails(tempFile);

            Files.deleteIfExists(tempFile);

            return result;

        } catch (Exception e) {

            return "Gemini certificate analysis failed: "
                    + e.getMessage();
        }
    }


    // =========================
    // ADD CERTIFICATE
    // =========================
    @PostMapping
    public Certificate addCertificate(@RequestBody Certificate certificate) {

        return repository.save(certificate);
    }


    // =========================
    // GET ALL CERTIFICATES
    // =========================
    @GetMapping
    public List<Certificate> getAllCertificates() {

        return repository.findAll();
    }


    // =========================
    // GET CERTIFICATE BY ID
    // =========================
    @GetMapping("/{id}")
    public Optional<Certificate> getCertificateById(
            @PathVariable Integer id) {

        return repository.findById(id);
    }


    // =========================
    // GET CERTIFICATES BY STUDENT ID
    // =========================
    @GetMapping("/student/{studentId}")
    public List<Certificate> getCertificatesByStudent(
            @PathVariable Integer studentId) {

        return repository.findByStudentId(studentId);
    }
    
    
    


    // =========================
    // UPDATE CERTIFICATE
    // =========================
    @PutMapping("/{id}")
    public Certificate updateCertificate(
            @PathVariable Integer id,
            @RequestBody Certificate certificate) {

        certificate.setId(id);

        return repository.save(certificate);
    }


    // =========================
    // DELETE CERTIFICATE
    // =========================
    @DeleteMapping("/{id}")
    public String deleteCertificate(
            @PathVariable Integer id) {

        repository.deleteById(id);

        return "Certificate deleted successfully.";
    }


    // =========================
    // OLD UPLOAD API
    // =========================
    // We will remove this later because
    // your final system should NOT permanently
    // store the uploaded certificate.
    @PostMapping("/upload")
    public String uploadCertificate(
            @RequestParam("file") MultipartFile file) {

        try {

            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath);

            return "Certificate uploaded successfully: " + fileName;

        } catch (IOException e) {

            return "Failed to upload certificate: " + e.getMessage();
        }
    }


    
    
}