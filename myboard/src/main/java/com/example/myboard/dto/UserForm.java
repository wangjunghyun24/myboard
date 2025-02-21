package com.example.myboard.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Setter
@ToString

/*エンティティの一部のデータをクライアントに配信するためのdtoです*/
public class UserForm {
    //private Long id;
    @Size(min = 3, max = 25)
    @NotEmpty(message = "Id empty")
    private String username;

    @NotEmpty(message = "password empty")
    private String password;

    @NotEmpty(message = "password check empty")
    private String password2;

    @NotEmpty(message = "Email Empty")
    private String email;

    //public User toEntity() {
        //return new User(id,username,password,email);
    }



