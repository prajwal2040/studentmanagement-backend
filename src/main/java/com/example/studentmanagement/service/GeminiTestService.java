package com.example.studentmanagement.service;

import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiTestService {

    public String testGemini() {

        Client client = new Client();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3.7-flash",
                        "Say hello and tell me that Gemini is working.",
                        null
                );

        return response.text();
    }
}