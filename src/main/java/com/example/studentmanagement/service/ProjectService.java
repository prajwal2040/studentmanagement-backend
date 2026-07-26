package com.example.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dto.GitHubRepositoryDTO;
import com.example.studentmanagement.entity.Project;
import com.example.studentmanagement.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private GitHubService gitHubService;
    
  

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
    
    public void importProjectsFromGithub(Integer studentId, String username) {

        List<GitHubRepositoryDTO> repositories = gitHubService.getRepositories(username);

        for (GitHubRepositoryDTO repo : repositories) {

            Project project = new Project();

            project.setStudentId(studentId);
            project.setGithubLink(repo.getGithubLink());
            project.setProjectType(repo.getName());

            projectRepository.save(project);
        }
    }
    public List<Project>getAllProject(){
    	return projectRepository.findAll();
    }
    
    public List<Project> getProjectsByStudent(Integer studentId) {
        return projectRepository.findByStudentId(studentId);
    }

}