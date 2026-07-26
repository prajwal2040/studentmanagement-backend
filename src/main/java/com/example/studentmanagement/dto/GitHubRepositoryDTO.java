package com.example.studentmanagement.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubRepositoryDTO {

    private String name;

    @JsonProperty("html_url")
    private String githubLink;

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }
}