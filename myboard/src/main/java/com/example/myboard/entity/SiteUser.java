package com.example.myboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.*;


@Entity
@Getter
@Setter
/*データベースのテーブルと1:1でマッピングされるクラス*/
public class SiteUser {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;

    @Column
    private String email;
}
