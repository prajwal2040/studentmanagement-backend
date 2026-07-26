package com.example.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(Integer studentId, String password) {

        User user = userRepository.findByStudentId(studentId);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }

}
