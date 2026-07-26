package com.example.studentmanagement.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.dto.DashboardDTO;
import com.example.studentmanagement.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{studentId}")
    public DashboardDTO getDashboard(@PathVariable Integer studentId) {
        return dashboardService.getDashboard(studentId);
    }
    
    @GetMapping("/all")
    public List<DashboardDTO> getAllDashboard() {

        return dashboardService.getAllDashboard();

    }
}