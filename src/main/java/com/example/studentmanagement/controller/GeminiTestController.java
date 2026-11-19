package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.service.GeminiTestService;

@RestController
public class GeminiTestController {

    @Autowired
    private GeminiTestService geminiTestService;

    @GetMapping("/gemini-test")
    public String testGemini() {

        return geminiTestService.testGemini();
    }
}