package com.example.studentmanagement.controller;
import java.util.List;
import com.example.studentmanagement.entity.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.service.ProjectService;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/import")
    public String importProjects(
            @RequestParam Integer studentId,
            @RequestParam String username) {

        projectService.importProjectsFromGithub(studentId, username);

        return "Projects imported successfully!";
    }
    @GetMapping
    public List<Project>getAllprojects(){
    	return projectService.getAllProject();
    }
    
    @GetMapping("/student/{studentId}")
    public List<Project> getProjectsByStudent(@PathVariable Integer studentId) {
        return projectService.getProjectsByStudent(studentId);
    }
}