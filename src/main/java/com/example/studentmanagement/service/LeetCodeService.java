package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.LeetCodeStats;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LeetCodeService {

    private final RestTemplate restTemplate = new RestTemplate();

    public LeetCodeStats getLeetCodeStats(String username) {

        String url = "https://leetcode.com/graphql";

        String query = """
                query userProfile($username: String!) {
                    matchedUser(username: $username) {
                        username
                        submitStatsGlobal {
                            acSubmissionNum {
                                difficulty
                                count
                                submissions
                            }
                        }
                    }
                }
                """;

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("query", query);

        Map<String, String> variables = new HashMap<>();
        variables.put("username", username);

        requestBody.put("variables", variables);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, request, Map.class);

        Map responseBody = response.getBody();

        Map data = (Map) responseBody.get("data");

        Map matchedUser = (Map) data.get("matchedUser");

        Map submitStatsGlobal =
                (Map) matchedUser.get("submitStatsGlobal");

        var acSubmissionNum =
                (java.util.List<Map>) submitStatsGlobal.get("acSubmissionNum");

        int totalSolved = 0;
        int easySolved = 0;
        int mediumSolved = 0;
        int hardSolved = 0;

        for (Map stat : acSubmissionNum) {

            String difficulty = (String) stat.get("difficulty");

            int count = (Integer) stat.get("count");

            if (difficulty.equals("All")) {
                totalSolved = count;
            }
            else if (difficulty.equals("Easy")) {
                easySolved = count;
            }
            else if (difficulty.equals("Medium")) {
                mediumSolved = count;
            }
            else if (difficulty.equals("Hard")) {
                hardSolved = count;
            }
        }

        return new LeetCodeStats(
                totalSolved,
                easySolved,
                mediumSolved,
                hardSolved
        );
    }
}