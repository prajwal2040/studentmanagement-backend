package com.example.studentmanagement.controller;
import java.util.List;
import com.example.studentmanagement.dto.GitHubRepositoryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.service.GitHubService;

@RestController
@RequestMapping("/github")
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;
    
    @GetMapping("/{username}")
    public List<GitHubRepositoryDTO> getRepositories(@PathVariable String username) {
        return gitHubService.getRepositories(username);
    }

   
}
