package com.example.studentmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.entity.Certificate;
import com.example.studentmanagement.repository.CertificateRepository;

@RestController
@RequestMapping("/certificate")
@CrossOrigin(origins = "*")
public class CertificateController {

    @Autowired
    private CertificateRepository repository;

    // POST API
    @PostMapping
    public Certificate addCertificate(@RequestBody Certificate certificate) {
        return repository.save(certificate);
    }

    // GET ALL API
    @GetMapping
    public List<Certificate> getAllCertificates() {
        return repository.findAll();
    }

    // GET BY ID API
    @GetMapping("/{id}")
    public Optional<Certificate> getCertificateById(@PathVariable Integer id) {
        return repository.findById(id);
    }
    @GetMapping("/student/{studentId}")
    public List<Certificate> getCertificatesByStudent(@PathVariable Integer studentId) {
        return repository.findByStudentId(studentId);
    }

    // UPDATE API
    @PutMapping("/{id}")
    public Certificate updateCertificate(@PathVariable Integer id,
                                         @RequestBody Certificate certificate) {

        certificate.setId(id);
        return repository.save(certificate);
    }

    // DELETE API
    @DeleteMapping("/{id}")
    public String deleteCertificate(@PathVariable Integer id) {

        repository.deleteById(id);
        return "Certificate deleted successfully.";
    }
}