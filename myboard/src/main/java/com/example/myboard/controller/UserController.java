package com.example.myboard.controller;

import com.example.myboard.entity.User;
import com.example.myboard.dto.UserForm;
import com.example.myboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    

}
