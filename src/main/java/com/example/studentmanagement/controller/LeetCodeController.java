package com.example.studentmanagement.controller;
import org.springframework.http.ResponseEntity;
import com.example.studentmanagement.dto.LeetCodeStats;
import com.example.studentmanagement.entity.LeetCodeProfile;
import com.example.studentmanagement.repository.LeetCodeProfileRepository;
import com.example.studentmanagement.service.LeetCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leetcode")
@CrossOrigin(origins = "*")
public class LeetCodeController {

    @Autowired
    private LeetCodeService leetCodeService;

    @Autowired
    private LeetCodeProfileRepository repository;


    // =========================
    // SAVE LEETCODE PROFILE
    // =========================

    @PostMapping("/profile")
    public LeetCodeProfile saveProfile(
            @RequestBody LeetCodeProfile profile) {

        LeetCodeProfile existingProfile =
                repository.findByStudentId(profile.getStudentId())
                        .orElse(null);

        if (existingProfile != null) {
            return existingProfile;
        }

        return repository.save(profile);
    }

    // =========================
    // GET SAVED PROFILE
    // =========================

    @GetMapping("/profile/{studentId}")
    public ResponseEntity<LeetCodeProfile> getProfile(
            @PathVariable Integer studentId) {

        return repository.findByStudentId(studentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }


    // =========================
    // GET LEETCODE STATISTICS
    // =========================

    @GetMapping("/stats/{username}")
    public LeetCodeStats getLeetCodeStats(
            @PathVariable String username) {

        return leetCodeService.getLeetCodeStats(username);
    }
}