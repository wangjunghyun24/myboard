package com.example.myboard.controller;

import com.example.myboard.Service.UserService;
import com.example.myboard.dto.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.ObjectError;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/signup")
    public String Testform() {
        return "user/sign";
    }
    //会員登録
    @PostMapping("/user/join")
    public String createUser(@Valid UserForm userCreateForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());

            model.addAttribute("errors", errorMessages);
            return "user/sign";
        }

        if (!userCreateForm.getPassword().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());

            model.addAttribute("errors", errorMessages);
            return "user/sign";
        }

        userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword());

        return "redirect:/user/login";
    }
    //ログイン
    @GetMapping("/user/login")
    public String loginform() {
        return "user/login";
    }


}
