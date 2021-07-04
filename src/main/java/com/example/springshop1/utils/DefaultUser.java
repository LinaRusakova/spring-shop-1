package com.example.springshop1.utils;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DefaultUser {

    private String login;
    private String password;

    private String firstName;
    private String lastName;
    private String secondName;
    private String phone;
    private String email;
}