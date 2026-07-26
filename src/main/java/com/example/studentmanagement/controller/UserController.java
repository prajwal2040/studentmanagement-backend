package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Integer login(
            @RequestParam Integer studentId,
            @RequestParam String password) {


        boolean success = userService.login(studentId, password);


        if(success) {

            return studentId;

        } 
        else {

            return null;

        }

    }

}
