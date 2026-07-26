package com.example.studentmanagement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.studentmanagement.dto.GitHubRepositoryDTO;

@Service
public class GitHubService {

    @Autowired
    private RestClient.Builder restClientBuilder;

    private RestClient restClient() {
        return restClientBuilder.build();
    }

    public List<GitHubRepositoryDTO> getRepositories(String username) {

        String url = "https://api.github.com/users/" + username + "/repos";

        GitHubRepositoryDTO[] repositories = restClient()
                .get()
                .uri(url)
                .retrieve()
                .body(GitHubRepositoryDTO[].class);

        return Arrays.asList(repositories);
    }
}